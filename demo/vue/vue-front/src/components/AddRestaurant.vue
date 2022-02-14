<template>
  <div>
    <div id="main-contents">
      
        <h1 class="mt-4 mb-4">리뷰쓰기</h1>
        <b-card class="w-25 mr-auto ml-auto" bg-variant="light">

				<b-form-group
                    label="식당명:"
                    label-for="resName"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resName" v-model="resName"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="식당분류:"
                    label-for="resType"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resType" v-model="resType"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="주소:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="상세주소:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="휴일:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="위도:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="경도:"
                    label-for="userReview"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="userReview" v-model="userReview"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="대표메뉴:"
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
                <b-button to="/" @click="axProtocol(), toast('b-toaster-top-center')" class="mt-3">작성</b-button>
            </b-card>
        
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
            resId: this.wlist.data[0].resId,
            userId:"",
            userReview:"",
            image:null,

            // wlist:null,
            // wreview: null,
        };
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
