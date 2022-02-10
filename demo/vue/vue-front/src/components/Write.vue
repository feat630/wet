<template>
  <div>
    <div id="main-contents">
      
        <h1 class="mt-4 mb-4">리뷰쓰기</h1>
            <b-card class="w-25 mr-auto ml-auto" bg-variant="light">
                <b-form-group
                    label="음식점:"
                    label-for="resId"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input disabled v-model="wlist.data[0].resName" id="resId"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="닉네임:"
                    label-for="userId"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userId" v-model="userId"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="리뷰:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="사진첨부:"
                    label-for="image"
                    label-cols-sm="3"
                    label-align-sm="right"
                    style="text-align:center"
                >
                    <b-form-file
                        id="image"
                        v-model="image"
                        multiple
                        :state="Boolean(image)"
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
                <b-button to="/" @click="axProtocol(), toast('b-toaster-top-center')" class="mt-3">작성</b-button>
            </b-card>
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
    props: {
        wlist: {
            type: Object,
            default: "",
        }
    },
    data() {
        return {
            resId: this.wlist.data[0].resId,
            userId:"",
            userReview:"",
            image:null,
            rating: 3,

            // wlist:null,
            // wreview: null,
        };
    },
    computed: {
        setParams() {
            const params = {
                resId: this.resId,
                userId: this.userId,
                userReview: this.userReview,
                // image: this.image,
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
        toast(toaster, append = false) {
            this.$bvToast.toast(`리뷰작성완료!`, {
            title: `알람`,
            toaster: toaster,
            solid: true,
            appendToast: append
        })
    },
    axProtocol(){
    http
        .post("/review/write", this.setParams)
        .then(response=>{
           console.log(response);
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
