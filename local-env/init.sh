awslocal dynamodb create-table \
  --table-name todolist \
  --key-schema \
      AttributeName="Task Id",KeyType=HASH \
  --attribute-definitions \
      AttributeName="Task Id",AttributeType=S \
  --billing-mode PAY_PER_REQUEST