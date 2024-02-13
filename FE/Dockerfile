FROM node:20.11.0

RUN apt-get update && apt-get install -y xsel

WORKDIR /home/ubuntu/S10P12C211/FE

COPY package*.json ./

ADD . .
RUN npm install -g npm
RUN npm install
# RUN npm install @webzlodimir/vue-bottom-sheet

# Make sure the 'serve' script is defined in package.json
RUN npm install -g serve

COPY . .
RUN npm run build
EXPOSE 8081

CMD ["serve", "-s", "dist", "-l", "8081"]
