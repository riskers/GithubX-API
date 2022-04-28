## Star API

> path: `/api/star/`

### Search 

> Search stars by params

```
GET ${path}/search
```

#### params

* groupId: the repo's groupId
* tagId: the repo's tagId
* fullName: the repo's fullName, ps. `riskers/githubx-api`

#### example

```
curl http://localhost:8910/api/star/search
```

Response:

```
Status: 200
```

```json
{
	"code": 0,
	"message": "success",
	"data": [{
		"_id": "626a3373a15bdc2ae81d7d52",
		"htmlUrl": "https://github.com/socketio/socket.io-client",
		"fullName": "socketio/socket.io-client",
		"groupId": 0,
		"tagsId": [
			1
		],
		"createTime": 1650772687386,
		"updateTime": 1650772687386,
		"group": [{
			"_id": "626a3373a15bdc2ae81d7d53",
			"id": 0,
			"name": "UNGROUP"
		}],
		"tags": [{
			"_id": "626a3373a15bdc2ae81d7d54",
			"id": 1,
			"name": "a"
		}]
	}]
}
```

### Clear

> Drop collection

```
POST ${path}/clear
```

#### params

Not Need

#### example

```
curl http://localhost:8910/api/star/clear
```

Response:

```
Status: 200
```

```json
{
  "code": 0,
  "message": "success",
  "data": {}  
}
```

### BatchSave

> batch save

```
POST ${path}/batchSave
```

#### params

* List<Star>

#### example

```
curl http://localhost:8910/api/star/batchSave
```

Response:

```
Status: 200
```

```json
{
  "code": 0,
  "message": "success",
  "data": 680 // save count
}
```
