#
# This is based on the helpful tutorial here:
# https://medium.com/@tiangolo/react-in-docker-with-nginx-built-with-multi-stage-docker-builds-including-testing-8cc49d6ec305
#

# Node build image.
FROM tiangolo/node-frontend:10 as build-stage

WORKDIR /app

#
# Copy package.json and package-lock.json to the build image.
#
COPY package.json /app/
COPY package-lock.json /app/

#
# Install Node packages.
#
RUN npm install

#
# Copy code, images, build scripts, etc. to the build image.
#
COPY ./ /app/

#
# Run the build.
#
RUN npm run build

#
# Pull runtime Nginx image.
#
FROM nginx:1.15

#
# Copy the built application to the image.
#
COPY --from=build-stage /app/build/ /usr/share/nginx/html

#
# Copy the conf file I have configured into the Nginx image.
#
COPY --from=build-stage /app/default.conf /etc/nginx/conf.d/default.conf