FROM python:3.9

# 작업 디렉토리를 설정합니다.
WORKDIR /home/ubuntu/S10P12C211/Flask

# 필요한 파일을 복사하고 패키지를 설치합니다.
COPY requirements.txt .
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx
RUN pip install --no-cache-dir -r requirements.txt
RUN pip install flask_cors
RUN pip install --upgrade ultralytics
RUN pip install --force-reinstall ultralytics
RUN pip install gunicorn

# Flask 애플리케이션 파일을 복사합니다.
COPY app.py .
# 환경 변수를 설정합니다.


# Flask 애플리케이션을 실행합니다.
CMD ["gunicorn", "app:app", "-w", "4", "--bind", "0.0.0.0:5000"]