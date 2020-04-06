#DynanoDB
Below steps for windows only

1)Download  DynamoDB local instance
2) Start DynamoDB
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

3) Check Existing table if any 
	aws dynamodb list-tables --endpoint-url http://localhost:8000
	
4)Create Table to support this code 
    aws dynamodb create-table --cli-input-json file://EmployeeData.json --endpoint-url http://localhost:8000

5) Above json file and terraform script to create actual in aws is part of infra.


# Create DynamoDB Table using Terraform

1) Configure aws details in input.tfvars  as required input params

2) Init cmd: terraform init

3) Check configuration to execute and write a plan for target infrastructure cmd: terraform plan -var-file="input.tfvars"

4) Execute the plan cmd: terraform apply -var-file="input.tfvars"

5) Output: 
   Tablename  = Employee
6) Teardown cmd : terraform destroy -var-file="input.tfvars"


# Spring Dynamodb POC
This application expose CRUD DynamoDB operation with mapstruct,version controller, java inheritance with support arbitary data type
   


