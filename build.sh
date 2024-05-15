#!/usr/bin/env bash

set -e

# Grab current working directory
ROOT=$(pwd)
echo Current working directory: $ROOT

# Build web application
cd src/frontend
npm install
npm run build

# Build API
cd $ROOT
mvn clean package