awslocal dynamodb create-table \
  --table-name todolist \
  --key-schema \
      AttributeName="Task Id",KeyType=HASH \
      AttributeName="Task Name",KeyType=RANGE \
  --attribute-definitions \
      AttributeName="Task Id",AttributeType=S \
      AttributeName="Task Name",AttributeType=S \
  --billing-mode PAY_PER_REQUEST