package com.kc.batch.dest.dao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.queries.NumericRangeQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;
import com.couchbase.client.java.search.result.SearchQueryRow;

//@Repository
public class CouchbaseImpl {

	public void docIdQueryMethod(List<Long> ids) {
		
        Cluster cluster = CouchbaseCluster.create("localhost");
        cluster.authenticate("Administrator", "Kamal!23");
		Bucket travelSample = cluster.openBucket("travel-sample");
        String indexName = "travel-sample-index-unstored";
        
        Long min = ids.get(0);
        Long max = ids.get(ids.size()-1);

        NumericRangeQuery query = SearchQuery.numericRange().min(min).max(max).field("hotel_id");

        SearchQueryResult result = travelSample.query(
            new SearchQuery(indexName, query));

        System.out.println("Status!!!!!!!!!!!!!!! "+result.status());;
        for (SearchQueryRow row : result) {
            System.out.println(row.id());
        }
        
    }
	
}
