<template>
  <b-row>
    <b-col></b-col>
    <b-col>
      <h1>회원가입</h1>
	  <b-form v-if="show">
		  <b-card class="card">
			<b-form-group
			id="input-group-1"
			label="ID"
			label-for="input-1"
			style="display:grid"
			>

			<b-row align-h="between">
				<b-col cols="9">
					<b-form-input
						id="input-1"
						v-model="form.userId"
						placeholder="ID를 입력해주세요"
						style="display:inline;"
						required
					></b-form-input>
				</b-col>
				
				<b-col cols="3" style="text-align:right;">
					<b-button class="check" style="grid-row-end">중복확인</b-button>
				</b-col>
			</b-row>
			</b-form-group>

			<b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
			<b-form-input
				id="input-2"
				v-model="form.userPw"
				type="password"
				placeholder="비밀번호를 입력해주세요"
				required
			></b-form-input>
			</b-form-group>

			<b-form-group id="input-group-2" label="비밀번호 재확인" label-for="input-2">
			<b-form-input
				id="input-2"
				v-model="form.userPw"
				type="password"
				placeholder="비밀번호를 동일하게 입력해주세요"
				required
			></b-form-input>
			</b-form-group>

			<b-form-group id="input-group-2" label="이메일" label-for="input-2" description="비밀번호 분실시 사용됩니다.">
			<b-form-input
				id="input-2"
				v-model="form.userEmail"
				type="email"
				placeholder="이메일을 입력해주세요"
				required
			></b-form-input>
			</b-form-group>
			<b-row>
				<b-col>
					<b-link to="/login">돌아가기</b-link>
				</b-col>
				<b-col style="text-align:right">
					<b-button to="/login" @click="signProtocol" variant="primary">Submit</b-button>
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
      form: {
        userId: "",
        userPw: "",
		
		userEmail:"",
      },
	  check:false,
      show: true,
    };
  },
  methods: {
    signProtocol() {
		http
		.post("/user/signIn", this.form)
        .then(response=>{
        	console.log(response);
        })
        .catch(e=>{
           	console.log(e);
        });
    },
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
