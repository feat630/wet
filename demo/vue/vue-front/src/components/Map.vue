<template>
  <keep-alive>
  <div>
    <div id="map" 
    :class="{centerAni: isCenter, leftAni: !isCenter}"></div>
    <b-button-group :class="{centerAni: isCenter, leftBtn: !isCenter}">
      <b-button variandt="outline-primary" v-on:click="randomPick">목표장소</b-button>
      <b-button variandt="outline-primary" v-on:click="returnGeolocation">현재장소</b-button>
      <b-button variandt="outline-primary" v-on:click="howToGo">도착지까지</b-button>
      <b-button variandt="outline-primary" v-on:click="newRandom()">새로뽑기</b-button>
      <b-button v-b-toggle.sidebar-right @click="isCenter = !isCenter" >Go to Sidebar</b-button>
    </b-button-group>
    <!-- <h3 v-for="list in wlist.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3>
    <p>{{wlist.data[0].resId}}</p>
    <p>{{lat}}</p>
    <p>{{lon}}</p> -->
    
    <!-- <p>{{lateee.resName}}</p> -->
    <!-- <h3 v-for="list in lateee.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3> -->
    <b-sidebar id="sidebar-right" title="식당정보" style="width," right shadow>
      <div id="sidebar-div" class="px-3 py-3 ">
        
        <p>{{wlist.data[0].resType}}</p>
        <p>{{wlist.data[0].resName}}</p>
        <p>{{wlist.data[0].resAddrRoad}}</p>
        <p>{{wlist.data[0].resHoliday}}</p>
        <p>{{wlist.data[0].resFamousMenu}}</p>
        <!-- <b-table striped hover :items="wlist.data"></b-table> -->
        
      </div>
    </b-sidebar>

  </div>
  </keep-alive>
</template>

<script>
import http from "../http-common";

export default {
  name: "KakaoMap",
  data() {
    return {
      isCenter: true,
      map: null,
      markers: [],
      infowindow: null,
      wlist:null,
      newData: [],
      lat: null,
      lon: null,
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
    axProtocol(){
      http
        .get("/wet/one")
        .then(response=>{
          this.wlist = response.data;
          this.lat = this.wlist.data[0].resLat;
          this.lon = this.wlist.data[0].resLong;
          //this.drawMap()
        })
        .catch(e=>{
          console.log(e);
        })
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
        this.drawMap();
      }, 100); 
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  width: 65%;
  height: 400px;
  margin: auto;
}
/* 출력할 때는 왼쪽에서 오른쪽으로 */
/* .v-enter {
  width: 70%;
  height: 400px;
  margin: auto;
} */
/* 사라질 때는 아래로 */
#sidebar-right {
  width: 30%;
}
.leftAni {
  /* width: 65%;
  height: 400px;
  margin: auto; */
  transform: translateX(-23.5%);
  transition-duration: 0.45s;
  animation-timing-function: linear;
}

.leftBtn{
  transform: translateX(-50%);
  transition-duration: 0.45s;
  animation-timing-function: linear;
}

.centerAni {
  /* width: 65%;
  height: 400px;
  margin: auto; */
  transform: translateX(0%);
  transition-duration: 0.45s;
  animation-timing-function: linear;
}
</style>
