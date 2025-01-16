#!/bin/bash

# Set requirepass and authenticate
redis-cli config set requirepass password
redis-cli -a password auth password

# Bring Redis server to the foreground
redis-server
