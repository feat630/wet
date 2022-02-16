<template>
  <div>
    <div id="main-contents">
      
        <h1 class="mt-4 mb-4">리뷰쓰기</h1>
        <b-card class="w-40 mr-auto ml-auto" bg-variant="light">

				<b-form-group
                    label="* 식당명:"
                    label-for="resName"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resName" :state="Boolean(resName)" v-model="resName"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="* 식당분류:"
                    label-for="resType"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resType" :state="Boolean(resType)" v-model="resType"></b-form-input>
                </b-form-group>

                <b-form-group
                    label="* 주소:"
                    label-for="resAddr"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resAddr" v-model="resAddr" style="width:73%; display:inline" @click="openDaumPostcode" readonly></b-form-input>
					<b-button name="resAddr" @click="openDaumPostcode">주소 검색</b-button>
					<div ref="wrap" style="display:none;border:1px solid;width:380px;height:490px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" @click="foldDaumPostcode" alt="접기 버튼">
					</div>

                </b-form-group>

				<b-form-group
                    label="상세주소:"
                    label-for="resAddrDetail"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resAddrDetail" v-model="resAddrDetail"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="휴일:"
                    label-for="resHoliday"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resHoliday" v-model="resHoliday"></b-form-input>
                </b-form-group>

				<b-form-group
                    label="대표메뉴:"
                    label-for="resFamousMenu"
                    label-cols-sm="3"
                    label-align-sm="right"
                >
                    <b-form-input id="resFamousMenu" v-model="resFamousMenu"></b-form-input>
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
                        
                        placeholder="Choose a file"
                        drop-placeholder="Drop file here..."
                    ></b-form-file>
                </b-form-group>
                *표시는 필수항목<br>
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
import kakaoLocation from "../kakao-api"

export default {
    name: "Review",
    data() {
        return {
			resName:"",
			resType:"",
			resAddrRoad: "",
			resAddr:"",
			resAddrJibun:"",
			resAddrDetail:"",
			resHoliday:"",
			resLat:"",
			resLong:"",
			resFamousMenu:"",
            userId:"",
            userReview:"",
            image:null,
			rating:"",
			result:[],
            // wlist:null,
            // wreview: null,

			geocoder : window.daum.maps.services.Geocoder(),
			
        };
    },
	computed: {
        setParams() {
            const params = {
                resName: this.resName,
                resType: this.resType,
                resAddrRoad: this.resAddr,
				resAddrJibun: this.resAddrJibun,
				resHoliday: this.resHoliday,
				resLat: this.resLat,
				resLong: this.resLong,
				resFamousMenu: this.resFamousMenu,
                // image: this.image,
                rating: this.rating,
            };
            return params;
        }
    },
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
			.post("/restaurant/add", this.setParams)
			.then(response=>{
			console.log(response);
			})
			.catch(e=>{
				console.log(e);
			});
		},
		kakaoProtocol() {
			kakaoLocation
				.get("/address.json", 
				{
					params: {
						query: (this.resAddr)
					}
				} )
				.then(response=>{
					this.result = response.data;
					this.resLat = this.result.documents[0].y;
					this.resLong = this.result.documents[0].x;
						})
				.catch(e=>{
					console.log(e);
						})
		},
		openDaumPostcode () {
			const elementWrap = this.$refs.wrap
			// 현재 scroll 위치를 저장해놓는다.
			const currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop)
			new window.daum.Postcode({
				oncomplete: (data) => {
					// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					let addr = '' // 주소 변수
		
					// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						addr = data.roadAddress
					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						addr = data.jibunAddress
					}
		
					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					// document.getElementById('address').value = addr
					this.resAddr = addr
					this.resAddrRoad = data.roadAddress
					this.resAddrJibun = data.jibunAddress
					this.kakaoProtocol()
					// iframe을 넣은 element를 안보이게 한다.
					// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
					elementWrap.style.display = 'none'
		
					// 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
					document.body.scrollTop = currentScroll
				},
				// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
				onresize: function (size) {
					elementWrap.style.height = size.height + 'px'
				},
				width: '100%',
				height: '100%'
			}).embed(elementWrap)
			elementWrap.style.display = 'block'
		},
		foldDaumPostcode () {
			const elementWrap = this.$refs.wrap
			elementWrap.style.display = 'none'
		}

  	}, 
	mounted() {
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
.w-40 {
	width: 35%;
}
</style>
