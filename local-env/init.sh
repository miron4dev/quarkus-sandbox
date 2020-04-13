awslocal dynamodb create-table \
  --table-name todolist \
  --key-schema \
      AttributeName="UserId",KeyType=HASH \
      AttributeName="TaskId",KeyType=RANGE \
  --attribute-definitions \
      AttributeName="UserId",AttributeType=S \
      AttributeName="TaskId",AttributeType=S \
  --billing-mode PAY_PER_REQUEST