# Use an official Node.js image as the base
FROM node:alpine

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy package.json and package-lock.json (if available)
COPY package*.json ./


# Install dependencies
RUN npm install -g npm@10.5.2

RUN npm install

# Copy the rest of the application code
COPY . .

# Expose the port the app runs on
EXPOSE 4200

# Command to run the application
CMD ["npm", "start"]
