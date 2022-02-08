<template>
  <div>
    <div id="main-contents">
      
        <h1 class="mt-4 mb-4">리뷰쓰기</h1>
            <b-card class="w-25 mr-auto ml-auto" bg-variant="light">
                <b-form-group
                    label="음식점이름:"
                    label-for="nested-street"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input disabled v-model="$attrs.wlist.data[0].resName" id="nested-street"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="닉네임:"
                    label-for="nested-city"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userId" v-model="userId"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="리뷰:"
                    label-for="nested-state"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="사진 첨부:"
                    label-for="nested-country"
                    label-cols-sm="3"
                    label-align-sm="right"
                    style="text-align:center"
                >
                    <b-form-file
                        v-model="image"
                        multiple
                        :state="Boolean(file1)"
                        placeholder="Choose a file"
                        drop-placeholder="Drop file here..."
                    ></b-form-file>
                </b-form-group>

                <b-form-group
                    label="별점"
                    label-cols-sm="3"
                    label-align-sm="right"
                    class="mb-0"
                    
                >
                    <b-form-rating v-model="rating"></b-form-rating>
                </b-form-group>
                <b-button @click="axProtocol()" class="mt-3">작성</b-button>
            </b-card>
        <p>{{$attrs.wlist.data[0].resName}}</p>
        <p>{{rating}}</p>
        <p>{{userId}}</p>
        <p>{{userReview}}</p>
        <p v-for="item in image" :key="item.name">{{item.name}}|{{item.lastModified}}</p>
 
        
    </div>
  
    <!-- <h3 v-for="list in wreview.data" :key="list.userId">{{list.rating}}  {{list.userReview}}</h3> -->
    <!-- <p>{{wlist.data[list].resId}}</p>
    <p>{{lat}}</p>
    <p>{{lon}}</p> -->
    
    <!-- <p>{{lateee.resName}}</p> -->
    <!-- <h3 v-for="list in lateee.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3> -->
    

  </div>
</template>

<script>
import http from "../http-common";

export default {
    name: "Review",
    data() {
        return {
            resId: $attrs.wlist.data[0].resId,
            userId:"",
            userReview:"",
            image:"",
            rating: 3,

            // wlist:null,
            // wreview: null,
        };
    },
    computed: {
        setParams() {
            const params = {
                userId: this.userId,
                userReview: this.userReview,
                image: this.image,
                rating: this.rating,
            };
            return params;
        }
    },
    // props:{
    //     wlist:{
    //         type: Array,
    //         default: ''
    //     }
    // },
    methods: {
    
    axProtocol(){
    http
        .post("/review/write", this.setParams)
        .then(response=>{
           
        })
        .catch(e=>{
            console.log(e);
        });
    },
  }
  , mounted() {
    // this.axProtocol();
    
    // // setTimeout(this.drawMap(), 2000);
    // setTimeout(() => {
    //   this.axProtocol1();
    //   this.drawMap();
    // }, 100); 
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.d-block {
    text-align: left;
}
</style>
