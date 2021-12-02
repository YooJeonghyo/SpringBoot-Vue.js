<template lang="">
  <div>
    <v-container>
      <v-row row wrap align-center class="mt-5">
        <v-col cols="12" md="4"></v-col>
        <v-col cols="12" md="4">
          <v-form>
            <v-text-field
              label="아이디를 입력해 주세요"
              placeholder="id"
              v-model="memberId"
              outlined
            ></v-text-field>
            <v-text-field
              label="패스워드를 입력해 주세요"
              placeholder="password"
              type="password"
              v-model="password"
              outlined
            ></v-text-field>
          </v-form>
          <v-btn block color="primary" dark @click="loginSubmit()"
            >로그인</v-btn
          >
          <!-- <v-checkbox label="ID 저장"></v-checkbox> -->
          <div id="app">
            <input
              type="checkbox"
              id="checkbox"
              v-model="checked"
              class="mt-5 mb-5"
            />
            <label for="checkbox"> ID 저장</label>
          </div>

          <span>
            <p @click="toSign">ID신청</p>
            <p @click="dialog = true">비밀번호 찾기</p>
          </span>
        </v-col>
        <v-col cols="12" md="4"></v-col>
      </v-row>
    </v-container>

    <v-dialog v-model="dialog" max-width="400">
      <v-card>
        <v-card-title class="headline grey lighten-1" dark>
          비밀번호 찾기
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field label="아이디를 입력해 주세요"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field label="이메일을 입력해 주세요"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
          *ID 신청시 등록한 이메일로 임시비밀번호가 발송됩니다.
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false">
            찾기
          </v-btn>
          <v-btn text @click="dialog = false">
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import http from "../http-setting";
export default {
  mounted() {
    this.rememberCheck();
  },
  watch: {
    checked() {
      localStorage.setItem("checked", this.checked);
    },
    memberId() {
      localStorage.setItem("rememberId", this.memberId);
    },
  },
  data() {
    return {
      dialog: false,
      memberId: null,
      password: null,
      checked: false,
    };
  },
  methods: {
    toSign() {
      this.$router.push("/signup");
    },
    rememberCheck() {
      if (localStorage.getItem("checked") === "false") {
        localStorage.removeItem("rememberId");
        this.checked = false;
      } else if (localStorage.getItem("checked") === "true") {
        this.memberId = localStorage.getItem("rememberId");
        this.checked = true;
      }
    },
    loginSubmit() {
      let loginData = {};
      loginData.memberId = this.memberId;
      loginData.password = this.password;

      http
        .post("/login", loginData)
        .then((res) => {
          if (Object.keys(res.data).length !== 0) {
            sessionStorage.setItem("ID", res.data.id);
            sessionStorage.setItem("name", res.data.membername);
            alert(`${res.data.membername}님 환영합니다.`);
            this.$router.push({
              path: `/info/${res.data.id}`,
            });
            location.reload();
          } else {
            alert("아이디와 비밀번호를 확인해주세요");
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style scoped></style>
