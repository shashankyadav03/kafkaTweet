package elasticSearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.JSONArray;

public class insert {
	
//	public RestHighLevelclient createClient() {
//		String hostname = "kafkaprojectpoc-6781039780.us-east-1.bonsaisearch.net";
//
//			String username = "euqrk4jobe";
//				
//			String password = "nq54ofc36n";
//
//			//don't do if you run a tool ES
//
//			final CredentialsProvider credentialsProvider = new BasicCredentials Provider(); credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
//
//			RestClientBuilder builder - RestClient.builder
//
//			new HttpHost(hostname, port 443, scheme: "https")
//
//			.setHttpClientConfig callback(new RestClientBuilder HttpClientConfigCallback) (
//
//			@Override
//
//			public HttpAsyncclientBuilder customizekttpClient return httpClientBuilder. setDefaultCredentialsProvider
//
//			(HttpAsyncClient Builder httpClient Builder) (
//
//			(credentialsProvide
//
//			RestHighLevelclient client = new RestHighLevelClient(builder); 
//			return client:
//
//			
//	}
	
	
	public static RestHighLevelClient createClientB() {
		
		  
				String hostname = "kafkaprojectpoc-6781039780.us-east-1.bonsaisearch.net";
		
				String username = "euqrk4jobe";
					
				String password = "nq54ofc36n";
		
		  CredentialsProvider cp = new BasicCredentialsProvider();
		  cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

		  RestHighLevelClient rhlc = new RestHighLevelClient(
		    RestClient.builder(new HttpHost(hostname, 443, "https"))
		      .setHttpClientConfigCallback(
		        httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(cp)
		          .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())));

//		  // https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-search.html
//		  SearchRequest searchRequest = new SearchRequest();
//		  SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		  searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//		  searchRequest.source(searchSourceBuilder);
		  
		  
		  return rhlc;
		 
		 }
		
	
	public static void insertData(String tweet) throws IOException {
		//RestHighLeveLClient client = createClient();
		
		//org.slf4j.Logger logger =  LoggerFactory.getLogger(insert.class.getName());
		
		RestHighLevelClient client=createClientB();
		
		//String dummy="{\"first\":\"poc\"}";
		//JSONArray tweets = new JSONArray(tweet);
		
		IndexRequest indexRequest = new IndexRequest("twitter").source(tweet,XContentType.JSON);
		
		IndexResponse indexResponse = client.index(indexRequest,RequestOptions.DEFAULT);
		
		String id =indexResponse.getId();
		String ind =indexResponse.getIndex();
		System.out.println(ind+" -> "+id);
		
		client.close();
		
		
		
	}
}
