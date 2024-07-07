FROM nginx

COPY webapp /usr/share/nginx/html
COPY conf /etc/nginx/sites-available

ENTRYPOINT [ "nginx", "-g", "daemon off;" ]