#### Hdfs-File-Purge

Hdfs-File-Purge utility will help purge all the files in the given hdfs path if retention period is reached.

##### USAGE

```shell
hadoop jar [jarName] [configFile] [logPath]

hadoop jar filepurge-1.0-SNAPSHOT.jar config.properties /u/username/logs/
```

#### CONFIGURATION-FILE-TEMPLATE

```json
{"kerberos_principal":"",
"kerberos_keytab_path":"",
"hdfspatharray":
[ {"path":"/user/username/testApp1/appData1/","retention":"1","recursive_deletion":true}
]
}


```

#### CONFIGURATION-FILE-USAGE

To add a new hdfs location to the existing configuration file follow the steps below

#### **STEP 1: Adding appData with retention period of 2, just create a new json object as shown below**

```json
{"path":"/user/username/testApp/appData/","retention":"2","recursive_deletion":true}
```



#### STEP 2: Add the json obect created above to the hdfspatharray in the existing confiuration file. For this Ex, using the CONFIGURATION-FILE-TEMPLATE specified above

```json
{"kerberos_principal":"",
"kerberos_keytab_path":"",
"hdfspatharray":
[ {"path":"/user/username/testApp1/appData1/","retention":"1","recursive_deletion":true},
{"path":"/user/username/testApp/appData/","retention":"2","recursive_deletion":true},
]
}
```



#### CONFIGURATION-FILE-CONSTANTS

###### kerberos_principal:

If users of the tool prefers to authenticate kerberos using a specific ID they can provide kerberos_principle as input to the program. If not just just use "" or "null".

When users specify "" or "null" as kerberos_principal the tool will autogenerate key tab for the current user running the jar. If the user is proxy then auto generated keytab will be [user@DOMAIN.COM](mailto:proxy_user@HPC.FORD.COM)

Ex: "[user@DOMAIN.COM](mailto:user@HPC.FORD.COM)"

###### kerberos_keytab_path:

if kerberos_principal is set above to a specific user ([user@DOMAIN.COM](mailto:proxy_user@HPC.FORD.COM)) , please provide the path to the keytab as an input to the program through kerberos_keytab_path.

If kerberos_principal is set to "" or "null" then set kerberos_keytab_path to the same value as kerberos_principal

Ex: "/u/user/user.keytab"

###### hdfspatharray:

hdfspatharray represents the json array which holds all the given paths along with their retentions

###### path:

path denotes the hdfs location of the dataset

**Examples**:

/user/username/testApp/appData/

Or

hdfs://hdd2cluster/user/username/testApp/appData/

###### retention:

This represents the retention period of each hdfspath.Values provided as input to retention represents days. retention can also accepts decimal values. If the retention period is less than a day, for instance 12 hrs it can be achieved using following conversion.

**Examples**:

If a user wants to set retention to 6 hours, retention will be 0.25 as 6 hours is one-fourth in a day which evaluates to

retention = "0.25", evaluates to 6 hours (1/4) retention = "1", evaluates to one day retention = "2", evaluates to two days retention = "0.003472", which is equivalent to 5 min (1/(24*12))


