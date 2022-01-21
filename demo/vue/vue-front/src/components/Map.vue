<template>
  <div>
    <div id="map"></div>
    <h3 v-for="list in wlist.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3>
<h3 v-for="list in lateee.data" :key="list.resId">{{list.resName}}  {{list.resLat}}</h3>

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
      wlist:[],
      newData: [],
      lateee: null,
      lon: [],
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
          this.lateee = response.data;
        })
        .catch(e=>{
          console.log(e);
        })
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

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
