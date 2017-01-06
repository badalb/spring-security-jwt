INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES ('my-trusted-client','justzaap-authentication-service',NULL,'trust,read,write','client_credentials,authorization_code,implicit,password,refresh_token',NULL,'ROLE_USER,ROLE_TRUSTED_CLIENT',600,6000,NULL,NULL); 

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove) VALUES ('my-trusted-client-with-secret','justzaap-authentication-service','somesecret','trust,read,write','authorization_code,implicit,password,refresh_token',NULL,'ROLE_USER,ROLE_TRUSTED_CLIENT',30,60,NULL,NULL);

insert into tblrole values ('r1', 'ADMIN', 'ADMIN', 'N', now(), 'system', now(), 'system', 'N');
insert into tblrole values ('r2', 'USER', 'USER', 'N', now(), 'system', now(), 'system', 'N');


insert into tblpermission values ('p1', 'READ', 'READ', 'READ','N', now(), 'system','system', now(), 'N' );
insert into tblpermission values ('p2', ' WRITE', 'WRITE', 'WRITE','N', now(), 'system','system', now(), 'N' );

insert into tblrole_permission values ('rp1','p1','r1', now(), 'N', 'system', 'system', now(), 'N');
insert into tblrole_permission values ('rp2','p2','r2', now(), 'N', 'system', 'system', now(), 'N');

insert into tblrole_permission values ('rp3','p1','r1', now(), 'N', 'system', 'system', now(), 'N');
insert into tblrole_permission values ('rp4','p2','r2', now(), 'N', 'system', 'system', now(), 'N');


insert  into `tbluser`(`user_id`,`user_name`,`first_name`,`last_name`,`is_inactive`,`is_locked`,`password`,`created_date`,`last_login_date`,`last_modified_date`,`created_by`,`last_modified_by`,`is_deleted`) 
values ('sysusr1','admin','Admin','Admin','N','N','$2a$10$kNaOXaLegCdBA6ShYaGeQueBA0hPJwqnQC9xtRTWFpI0tnLy8OS2a','2014-10-10 10:10:05',NULL,NULL,NULL,NULL,'N');

insert  into `tbluser`(`user_id`,`user_name`,`first_name`,`last_name`,`is_inactive`,`is_locked`,`password`,`created_date`,`last_login_date`,`last_modified_date`,`created_by`,`last_modified_by`,`is_deleted`) 
values ('sysusr2','user','User','User','N','N','$2a$10$kNaOXaLegCdBA6ShYaGeQueBA0hPJwqnQC9xtRTWFpI0tnLy8OS2a','2014-10-10 10:10:05',NULL,NULL,NULL,NULL,'N');


insert  into `tbluser_role`(`user_role_id`,`user_id`,`role_id`,`is_default`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`,`is_deleted`) 
values ('usrr1','sysusr1','r1','N','2014-10-10 10:10:10','system','2014-10-10 10:10:10','system','N');

insert  into `tbluser_role`(`user_role_id`,`user_id`,`role_id`,`is_default`,`created_date`,`created_by`,`last_modified_date`,`last_modified_by`,`is_deleted`) 
values ('usrr2','sysusr2','r1','N','2014-10-10 10:10:10','system','2014-10-10 10:10:10','system','N');