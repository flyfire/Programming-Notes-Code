# 演示json
import json

data = "hello json"
file_name = "json.json"
json.dump(data, open(file_name, 'w'))

with open(file_name, 'r') as file_json:
    json_data = json.load(file_json)
    print(type(json_data))
    print(json_data)
