<template>
  <div class="hello">
    <h1>전체 목록</h1>
    <hr>
    <h3 v-for="list in nameList.data" :key="list.resId">{{list.resName}}</h3>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: 'HelloWorld',
  data(){
    return{
      nameList:[]
    }
  },
  methods:{
    axProtocol(){
      http
        .get("/wet/list")
        .then(response=>{
          this.nameList = response.data;
          console.log(response.data);
        })
        .catch(e=>{
          console.log(e);
        })
    }
  },
  mounted(){
    this.axProtocol();
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
