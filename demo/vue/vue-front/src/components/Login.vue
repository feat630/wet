<template>
  <b-row>
    <b-col></b-col>
    <b-col>
      <h1>로그인</h1>
      	<b-form>
			<b-card class="card">
				<b-form-group
				id="input-group-1"
				label="userId"
				label-for="input-1"
				>
				<b-form-input
					id="input-1"
					v-model="form.userId"
					placeholder="ID"
					required
				></b-form-input>
				</b-form-group>

				<b-form-group id="input-group-2" label="userPw" label-for="input-2">
				<b-form-input
					id="input-2"
					v-model="form.userPw"
					type="password"
					placeholder="PW"
					required
				></b-form-input>
				</b-form-group>
				<b-row>
					<b-col>
						<b-link to="/signup">회원가입</b-link><br>
						<b-link to="/signup">비밀번호 찾기</b-link>
					</b-col>
					<b-col style="text-align:right">
						<b-button @click="loginProtocol" to variant="primary">로그인</b-button>
					</b-col>
				</b-row>
			</b-card>
    	</b-form>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import http from "../http-common"
export default {
  data() {
    return {
		log:"",
		form: {
			userId: "",
			userPw: "",
		},
      show: true,
    };
  },
  computed: {
		setParams() {
            const params = {
				userId: this.userId,
				userPw: this.userPw,
			}
			return params;
		}
  },
  methods: {
    loginProtocol() {
		http
		.post("/user/login", this.form)
        .then(response=>{
			this.log = response.data;
        	if(this.log.data[0].loginCheck){
				this.$router.push('/')
			}
        })
        .catch(e=>{
           	console.log(e);
			console.log("실패")
        });
	}
  },
};
</script>
<style>
#input-group-1 {
  margin-top: 1rem;
}
#input-group-1,
#input-group-2,
#input-group-3 {
  margin-bottom: 2.5rem;
}
.card {
	text-align: left;
}
</style>