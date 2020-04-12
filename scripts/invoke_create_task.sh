#!/bin/bash

sam local invoke "CreateTaskFunction" -e create_task_event.json -t ../template.yml --docker-network host