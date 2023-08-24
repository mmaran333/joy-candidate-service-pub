# joy-candidate-service

## Workday Rest API integration

The joy-candidate-service consists of the PoC code to integrate with the workday REST apis. The postman collection is added to the github repository under the folder postman-collection.

The postman collections access the candidate service deployed in the dev env.

In case the endpoints like **'Get Prospect'** and **'Create Prospect'** under the section *workday_integration* in the postman collection throw an error, the reason might be that the access_token used to access the workday rest apis may have expired. To replace it hit the endpoint **'Replace expired access token'** under the section *workday_integration* in the postman collection. 

Since the workday integration was done as a PoC, obtaining a new access token using the refresh token is not covered.

We've kept the PoC code as part of the candidate service and pushed to the dev env because this was required for a demo to Bayada and the workday rest api integration part has not been extracted out of this service.

For more information on the workday rest api integration please refer to the doc *'Rest integration.docx'* added to this repo.
