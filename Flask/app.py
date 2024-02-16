from operator import truediv
import os
import json
from PIL import Image
from ultralytics import YOLO
from flask import Flask, jsonify, url_for, render_template, request, redirect, Response
from flask_cors import CORS
import numpy as np
import cv2
import math
import uuid

app = Flask(__name__)

RESULT_FOLDER = os.path.join('static')
app.config['RESULT_FOLDER'] = RESULT_FOLDER
CORS(app, resources={r'/*': {'origins': '*'}})

model = YOLO('best.pt')

@app.route('/', methods=['GET'])
def index():
    return 'Hello, Flask'

#파일 업로드
@app.route('/upload', methods=['POST'])
def upload_image():
    if 'file' not in request.files:
        return jsonify({'error': 'No file part'})
    file = request.files['file']
    if file.filename == '':
        return jsonify({'error': 'No selected file'})
    if file:
        filename = str(uuid.uuid4()) + os.path.splitext(file.filename)[1]
        file_path = os.path.join(app.config['RESULT_FOLDER'], filename)
        file.save(file_path)
        
        img = cv2.imread(file_path)
        classNames = ['roughly_drawn_character']
        # YOLO 모델로 객체 검출 수행
        result = model(img, stream=True)
        
        
        # 검출 결과를 이미지에 그리기
        for r in result:
            boxes = r.boxes
            for box in boxes:
                x1, y1, x2, y2 = box.xyxy[0]
                x1, y1, x2, y2 = int(x1), int(y1), int(x2), int(y2)
                cv2.rectangle(img, (x1, y1), (x2, y2), (255, 0, 255), 3)
                conf = math.ceil((box.conf[0] * 100)) / 100
                cls = int(box.cls[0])
                class_name = classNames[cls]
                label = f'{class_name}{conf}'
                t_size = cv2.getTextSize(label, 0, fontScale=1, thickness=2)[0]
                c2 = x1 + t_size[0], y1 - t_size[1] - 3
                cv2.rectangle(img, (x1, y1), c2, [255, 0, 255], -1, cv2.LINE_AA)
                cv2.putText(img, label, (x1, y1 - 2), 0, 1, [255, 255, 255], thickness=1, lineType=cv2.LINE_AA)

        # 결과 이미지 저장
        output_path = os.path.join(app.config['RESULT_FOLDER'], 'detected_' + filename)
        cv2.imwrite(output_path, img)

        # 파일 경로를 반환
        return jsonify({'success': True, 'file_path': output_path, 'message':class_name}), 200


if __name__ == '__main__':
    app.run()