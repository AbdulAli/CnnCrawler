<html>
<head>
  <title>Trump NEWS !!</title>
</head>
<body>
  <h1>News From CNN</h1>
  <ul>
    <#list cnnDataList as cnnDataList>
      <h3>${cnnDataList_index + 1}. ${cnnDataList.headline}</h3>
      	<p>${cnnDataList.completeNews} from (${cnnDataList.date}).</p>
    </#list>
  </ul>
  
  <hr>
  
  <h1>Twitter Tweets</h1>
  <ul>
 	<#list twitterDataList as twitterDataList>
      <h3>${twitterDataList_index + 1}.</h3>
      <p>${twitterDataList.body}</p>
      <p>Retweets: ${twitterDataList.numberOfRetweets} 
    </#list>
  </ul>

</body>
</html>