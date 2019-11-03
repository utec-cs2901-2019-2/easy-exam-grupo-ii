const elasticsearch = require('elasticsearch');
const client = new elasticsearch.Client({
   hosts: [ 'http://localhost:9200']
});
client.ping({
     requestTimeout: 30000,
}, function(error) {
     if (error) {
         console.error('Elasticsearch cluster is down!');
     } else {
         console.log('Everything is ok');
     }
});

client.indices.create({
      index: 'scotch.io-tutorial'
  }, function(error, response, status) {
      if (error) {
          console.log(error);
      } else {
          console.log("created a new index", response);
      }
});

// add a data to the index that has already been created
client.index({
     index: 'scotch.io-tutorial',
     id: '1',
     type: 'problems_list',
     body: {
         "Key1": "Content for key one",
         "Key2": "Content for key two",
         "key3": "Content for key three",
     }
 }, function(err, resp, status) {
     console.log(resp);
 });


const problems = require('problems/problems.json');
// declare an empty array called bulk
var bulk = [];
//loop through each problem and create and push two objects into the array in each loop
//first object sends the index and type you will be saving the data as
//second object is the data you want to index
problems.forEach(problem =>{
   bulk.push({index:{
                 _index:"scotch.io-tutorial",
                 _type:"problems_list",
             }
         })
  bulk.push(problem)
})
//perform bulk indexing of the data passed
client.bulk({body:bulk}, function( err, response  ){
         if( err ){
             console.log("Failed Bulk operation".red, err)
         } else {
             console.log("Successfully imported %s".green, problems.length);
         }
});
