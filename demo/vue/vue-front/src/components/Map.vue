<template>
  <div>
    <div id="map"></div>
    <b-button-group>
      <b-button variandt="outline-primary" v-on:click="randomPick">목표장소</b-button>
      <b-button variandt="outline-primary" v-on:click="howToGo">도착지까지</b-button>
      <b-button variandt="outline-primary" v-on:click="returnGeolocation">현재장소</b-button>
    </b-button-group>
    <!-- <h3 v-for="list in wlist.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3>
    <p>{{wlist.data[0].resId}}</p>
    <p>{{lat}}</p>
    <p>{{lon}}</p> -->
    <b-table striped hover :items="wlist.data"></b-table>
    <!-- <p>{{lateee.resName}}</p> -->
    <!-- <h3 v-for="list in lateee.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3> -->

  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "KakaoMap",
  data() {
    return {
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
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.displayMarker([[this.latitude, this.longitude]]);
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
    howToGo(){
      
    }
  }
  , mounted() {
    this.axProtocol();

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
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 70%;
  height: 400px;
  margin: auto;
}
</style>
