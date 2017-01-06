# spring-security-jwt

1. create mysql database with name uaa

2. To start the application from your favourite editor right click on Application.java ->Run As-> Java Application 
   or Package it to war file and deploy it to tomcat.
   a. authentication-service runs on port no 8070
   b. resource-server runs on port 8080
3. If you want to access any resource (http://localhost:8080/api/v1/me) it will throw you an error like 
   {"error":"unauthorized","error_description":"Full authentication is required to access this resource"}
   
4. So to access the resorce first we need to authenticate to authorization-server with client id, client secret, user id and password
   a. curl my-trusted-client:@localhost:8070/oauth/token -d grant_type=password -d username=user -d password=password
   {"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0ODM2MTcyMjMsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6ImY3YjM1NDdmLWM4YmMtNDNmYy1iZWE4LTAzMGM3MWIwZWM3YSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXX0.cbOGr8Vd8GS61inOOd_KomiM2OyA0YjNIC7qpFxzl3AQtE0LKZhLYdFKqGAkt50stOFaXr0AxEyZX99lRfZELjV35HfuLI_Df_bMMaSBIy0oZKykWtUjj6JZJQIpJ4iqFHF5IoKKWJnF2uhAIk2Agnhq2h-PkM0q7cVGNnBwpCXQjRi88s7Z59QqbJa2dxTlYWKIFG-BUI345CeixEu_tH7BbsWANgLQRk1iFf5ujGf6rvkA_nnrmSuJEacU1kkio35bEgHZLyI5IjArbRbrkaQeGtOibnsorL04ZfT4jCSiafy4zBj7wGVc4-COSJMYesOYrpJge4HbFdREeqP9Ng","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiYXRpIjoiZjdiMzU0N2YtYzhiYy00M2ZjLWJlYTgtMDMwYzcxYjBlYzdhIiwiZXhwIjoxNDgzNjE2ODE1LCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6ImFjYzg2YzU3LTg2ZTAtNDU1MC05MjBhLTNmMGIyYTA5MzU3OSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50In0.c-FrCPiLnC8-ip9FXAqsZEvZ1Zs-I9G4BeJ81jeYxOn4A3LOyQtSrexuxvxo6CBEzy2gGxxU7HWug38ckDNwlWiJGr2gWtUEasgHFNzfiD7_ikx2A9QP-Wz3rcySkKGaKns-tvHTbHI1eWoAhbs26YFIZIv7PCh1aoAJrnvG3jMnxTSjuj4qwBHv7xW9gPvOr2l6tUjG1GOx_ZHVZqc3uK0ipkrN9WtpOQS9YmK22oABlW3vF6vX9iVh6fS_WvsUYvwNwsz3KXg5LApU3eBnaW5g_FA3A61WnAGQ4uxJtZ2j8TqShLLTk2PicAbizNBNpCSzJMyzzIvzXaGlspLLrw","expires_in":599,"scope":"read write trust","jti":"f7b3547f-c8bc-43fc-bea8-030c7"}
   b. so we got a signed JWT token which has primarily three sections
      i. Header -> eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9
      ii. Payload -> eyJleHAiOjE0ODM2MTcyMjMsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6ImY3YjM1NDdmLWM4YmMtNDNmYy1iZWE4LTAzMGM3MWIwZWM3YSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50Iiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXX0.cbOGr8Vd8GS61inOOd_KomiM2OyA0YjNIC7qpFxzl3AQtE0LKZhLYdFKqGAkt50stOFaXr0AxEyZX99lRfZELjV35HfuLI_Df_bMMaSBIy0oZKykWtUjj6JZJQIpJ4iqFHF5IoKKWJnF2uhAIk2Agnhq2h-PkM0q7cVGNnBwpCXQjRi88s7Z59QqbJa2dxTlYWKIFG-BUI345CeixEu_tH7BbsWANgLQRk1iFf5ujGf6rvkA_nnrmSuJEacU1kkio35bEgHZLyI5IjArbRbrkaQeGtOibnsorL04ZfT4jCSiafy4zBj7wGVc4-COSJMYesOYrpJge4HbFdREeqP9Ng","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIiwidHJ1c3QiXSwiYXRpIjoiZjdiMzU0N2YtYzhiYy00M2ZjLWJlYTgtMDMwYzcxYjBlYzdhIiwiZXhwIjoxNDgzNjE2ODE1LCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6ImFjYzg2YzU3LTg2ZTAtNDU1MC05MjBhLTNmMGIyYTA5MzU3OSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50In0
      iii. signature -> c-FrCPiLnC8-ip9FXAqsZEvZ1Zs-I9G4BeJ81jeYxOn4A3LOyQtSrexuxvxo6CBEzy2gGxxU7HWug38ckDNwlWiJGr2gWtUEasgHFNzfiD7_ikx2A9QP-Wz3rcySkKGaKns-tvHTbHI1eWoAhbs26YFIZIv7PCh1aoAJrnvG3jMnxTSjuj4qwBHv7xW9gPvOr2l6tUjG1GOx_ZHVZqc3uK0ipkrN9WtpOQS9YmK22oABlW3vF6vX9iVh6fS_WvsUYvwNwsz3KXg5LApU3eBnaW5g_FA3A61WnAGQ4uxJtZ2j8TqShLLTk2PicAbizNBNpCSzJMyzzIvzXaGlspLLrw
5. Now if we use the access token as bearer token while accessing protected resource from resource server, resource server will
   decode the token with shared public key, validate and authorise and grant access
   a. curl -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsianVzdHphYXAtYXV0aGVudGljYXRpb24tc2VydmljZSJdLCJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNDgzNjIwMTI1LCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6IjY5NTI4ZWI4LTRiNTMtNGRlNy1hZjEwLWVlMGFmNDQyZTUyOSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50In0.F6hVRnfWp4bct3oYg8LV9BywqcMjrl2jPHeL7PRfjh2b9tsSTfOm-UPTDhPVLdkQoh293pn8MezBG--W-DtSkG0lNSBN16SuX5XddemH0ijnG17EGysXbqG-bqLlka8IM49AkJUfKNbZGcLq9MybsschIFClo4aoZyilYictJFiXCIVcJ-JHg1-JIdwZflpawLNkcaC2avhOE7onZBhkj-sspinza9aeBuLTsCc9ROWv8NMrWcAYgxeEPJERAnAnwXjxxbBERlNWyOpunfNFvzd5vSFlen4xOjd1AKK-oRyDEPo7P_8NOISpw-4upLK3BrqFdCaWKP72J1cnDf60zw","token_type":"bearer","refresh_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsianVzdHphYXAtYXV0aGVudGljYXRpb24tc2VydmljZSJdLCJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwiYXRpIjoiNjk1MjhlYjgtNGI1My00ZGU3LWFmMTAtZWUwYWY0NDJlNTI5IiwiZXhwIjoxNDgzNjI1NTI1LCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6IjE0MjBkZTU4LTkwNDgtNDE4NC1hY2ZmLTE4OGExMTAyYzEzYiIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50In0.Hco0K85-VmqQezj9QNIgSu2DlZ94s-srflp7DdMUtLf1YUJLzNm1J7qrvViECMEXF1V4zLrAJhUCTFt0lxb2iyycBxk4A5iSJytWNNjUaVAc1pFQh8uxJs_v9pqS2lT_2ip55veNo51KzufleXIJC9Xg_wTcCvqK6o2DdTlDtH5UyFYnMzRax3tNkvxgFvg-1V4YiVlmDHAl0YYqLhVtuzRtDN6aNwqnVmHlt_YQrIHhNWkZtPaZLANYQNFk9KvckrtGu2eBDPta7Gk1rqFYg8NGvE-JUJr7eD34U_NWBnf_IYAiblr-1NuG9twv3UoUaqhk-N2x32QaWHfO8rNQlQ"  http://localhost:8080/api/v1/me
      {"authentication":{"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":null,"tokenValue":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsianVzdHphYXAtYXV0aGVudGljYXRpb24tc2VydmljZSJdLCJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNDgzNjIwMTI1LCJhdXRob3JpdGllcyI6WyJSRUFEIl0sImp0aSI6IjY5NTI4ZWI4LTRiNTMtNGRlNy1hZjEwLWVlMGFmNDQyZTUyOSIsImNsaWVudF9pZCI6Im15LXRydXN0ZWQtY2xpZW50In0.F6hVRnfWp4bct3oYg8LV9BywqcMjrl2jPHeL7PRfjh2b9tsSTfOm-UPTDhPVLdkQoh293pn8MezBG--W-DtSkG0lNSBN16SuX5XddemH0ijnG17EGysXbqG-bqLlka8IM49AkJUfKNbZGcLq9MybsschIFClo4aoZyilYictJFiXCIVcJ-JHg1-JIdwZflpawLNkcaC2avhOE7onZBhkj-sspinza9aeBuLTsCc9ROWv8NMrWcAYgxeEPJERAnAnwXjxxbBERlNWyOpunfNFvzd5vSFlen4xOjd1AKK-oRyDEPo7P_8NOISpw-4upLK3BrqFdCaWKP72J1cnDf60zw","tokenType":"Bearer","decodedDetails":null},"authorities":[{"authority":"READ"}],"authenticated":true,"userAuthentication":{"details":null,"authorities":[{"authority":"READ"}],"authenticated":true,"principal":"user","credentials":"N/A","name":"user"},"clientOnly":false,"oauth2Request":{"clientId":"my-trusted-client","scope":["trust","read","write"],"requestParameters":{"client_id":"my-trusted-client"},"resourceIds":["justzaap-authentication-service"],"authorities":[],"approved":true,"refresh":false,"redirectUri":null,"responseTypes":[],"extensions":{},"refreshTokenRequest":null,"grantType":null},"credentials":"","principal":"user","name":"user"}}
      
     