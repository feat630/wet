import axios from "axios"

export default axios.create({
    baseURL: "https://dapi.kakao.com/v2/local/search/",
    headers:{
		"Content-type":"application/json",
        "Authorization":"KakaoAK c1b081d15d2c010124167bb60a51e28a",
    },
});