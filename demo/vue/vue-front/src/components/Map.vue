<template>
  <div>
    <div id="main-contents"> <!--:class="{centerAni: isCenter, leftAni: !isCenter}"> -->
      <div id="map"></div>
      <h1 class="mt-4">{{wlist.data[0].resName}}</h1>
      <b-button-group class="mt-4">
        <b-button variandt="outline-primary" v-on:click="randomPick">목표장소</b-button>
        <b-button variandt="outline-primary" v-on:click="returnGeolocation">현재장소</b-button>
        <b-button variandt="outline-primary" >도착지까지</b-button>
        <b-button variandt="outline-primary" v-on:click="newRandom()">새로뽑기</b-button>
        <!-- <b-button v-b-toggle.sidebar-right @click="isCenter = !isCenter" >더보기</b-button> -->
        <b-button v-b-toggle.sidebar-right >더보기</b-button>
      </b-button-group>
    </div>
  
    <!-- <h3 v-for="list in wreview.data" :key="list.userId">{{list.rating}}  {{list.userReview}}</h3> -->
    <!-- <p>{{wlist.data[list].resId}}</p>
    <p>{{lat}}</p>
    <p>{{lon}}</p> -->
    
    <!-- <p>{{lateee.resName}}</p> -->
    <!-- <h3 v-for="list in lateee.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3> -->
    <b-sidebar id="sidebar-right" title="식당정보" style="width," right shadow backdrop>
      <b-card
        :title=wlist.data[0].resName
        img-src="https://picsum.photos/600/300/?image=25"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 90%;"
        class="mb-2 ml-auto mr-auto"
      >
        <b-card-text>
          {{wlist.data[0].resType}} | {{wlist.data[0].resAddrRoad}}<br>
          {{wlist.data[0].resHoliday}}<br>
          {{wlist.data[0].resFamousMenu}}
        </b-card-text>
      </b-card>
      <b-card
        v-for="list in wreview.data" :key="list.userId"
        :title=list.resId
        img-src="https://picsum.photos/600/300/?image=25"
        img-alt="Image"
        img-top
        tag="article"
        style="max-width: 90%;"
        class="mb-2 ml-auto mr-auto"
      >
        <b-card-text> 
          {{list.userReview}}<br>
          {{list.image}}
          <b-form-rating v-model="list.rating" class="w-75 ml-auto mr-auto" readonly></b-form-rating>
        </b-card-text>
      </b-card>
      <div id="sidebar-div" class="px-3 py-3 text-center">
        <b-button :to="{name: 'write', params: {wlist}}" class="mr-auto ml-auto">리뷰쓰러가기</b-button>
      </div>
    </b-sidebar>

  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "KakaoMap",
  data() {
    return {
      isCenter: "",
      map: null,
      markers: [],
      infowindow: null,
      wlist:"",
      wreview: "",
      lat: "",
      lon: "",
    };
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 1,
      };
      this.map = new kakao.maps.Map(container, options);
      this.displayMarker([[this.lat, this.lon]]);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
            (position) =>
                new kakao.maps.Marker({
                  map: this.map,
                  position,
                })
        );

        const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    async axProtocol(){
      try{
        await http
          .get("/wet/one")
          .then(response=>{
            this.wlist = response.data;
            this.lat = this.wlist.data[0].resLat;
            this.lon = this.wlist.data[0].resLong;
          })
          .catch(e=>{
            console.log(e);
          });
        await http
        .get("/review/one", {
          params: {
            resId: this.wlist.data[0].resId
          }
        })
        .then(response=>{
          this.wreview = response.data;
        })
        .catch(e=>{
          console.log(e);
        });
      } catch (error) {
        console.log(error);
      }
    },
    axProtocol1(){
      
    },
    randomPick(){
      this.displayMarker([[this.lat, this.lon]]);
    },
    returnGeolocation(){
      this.displayMarker([[this.latitude, this.longitude]]);
    },
    newRandom(){
      this.axProtocol();
      
      setTimeout(() => {
        this.axProtocol1();
        this.displayMarker([[this.lat, this.lon]]);
      }, 100); 
    },
    drawMap() {
      navigator.geolocation.getCurrentPosition(pos => {
      this.latitude = pos.coords.latitude;
      this.longitude = pos.coords.longitude;

      if (window.kakao && window.kakao.maps) {

        this.initMap();

      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=26cf048b6fc3670e01f266ceb37d7f80";
        document.head.appendChild(script);
      }

    }, err => {
      alert(err.message);
    });
    },
    mapToggle() {
      this.isActive 
    }
  }
  , mounted() {
    this.axProtocol();
    
    // setTimeout(this.drawMap(), 2000);
    setTimeout(() => {
      this.axProtocol1();
      this.drawMap();
    }, 100); 
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 65%;
  height: 450px;
  margin: auto;
}
/* 출력할 때는 왼쪽에서 오른쪽으로 */
/* .v-enter {
  width: 70%;
  height: 400px;
  margin: auto;
} */
/* 사라질 때는 아래로 */

.leftAni {
  /* width: 65%;
  height: 400px;
  margin: auto; */
  transform: translateX(-15%);
  transition-duration: 0.47s;
  animation-timing-function: linear;
}

.centerAni {
  /* width: 65%;
  height: 400px;
  margin: auto; */
  transform: translateX(0%);
  transition-duration: 0.51s;
  animation-timing-function: linear;
}

.btn:focus, .btn:active{
  box-shadow: none;
  outline-color: none;
  outline: none;
}
</style>
