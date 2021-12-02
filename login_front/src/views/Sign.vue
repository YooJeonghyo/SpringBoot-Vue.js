<template>
  <v-form>
    <v-container fluid grid-list-sm pa-0>
      <v-row>
        <v-col cols="12" md="3"></v-col>
        <v-col cols="12" md="6">
          <h2 class="text-center mt-5 mb-5">ID 신청</h2>

          <v-text-field
            outlined
            label="아이디를 입력해 주세요 (영문소문자, 숫자 조합 6 ~ 15자)"
            placeholder="ID"
            :rules="[rules.type, rules.length(6)]"
            v-model="data.memberid"
            @keyup="resetDuplicate()"
          >
            <template v-slot:append-outer>
              <v-menu>
                <template v-slot:activator="{ attrs }">
                  <v-btn v-bind="attrs" @click="duplicate(data.memberid)">
                    중복 확인
                  </v-btn>
                </template>
              </v-menu>
            </template>
          </v-text-field>

          <v-text-field
            label="패스워드를 입력해 주세요 (영문소문자, 숫자 조합 6 ~ 15자)"
            placeholder="password"
            outlined
            :rules="[rules.type, rules.length(6)]"
            v-model="data.password"
            type="password"
          ></v-text-field>

          <v-text-field
            label="비밀번호 재확인"
            placeholder="password"
            outlined
            type="password"
            v-model="passwordCheck"
          ></v-text-field>

          <v-text-field
            label="사용자명"
            outlined
            v-model="data.membername"
          ></v-text-field>

          <v-text-field
            label="전화번호 (공백, - 없이 입력해주세요 예) 01033336666)"
            outlined
            v-model="data.phone"
            :rules="[rules.phone, rules.length(10)]"
          ></v-text-field>

          <v-text-field
            label="이메일"
            type="email"
            outlined
            v-model="data.email"
            :rules="[rules.email]"
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="3"></v-col>
      </v-row>

      <v-row>
        <v-col cols="12" md="5"></v-col>
        <v-col cols="12" md="1">
          <v-btn block color="primary" dark @click="signup">ID 신청</v-btn>
        </v-col>
        <v-col cols="12" md="1">
          <v-btn block color="blue-grey darken-1" dark @click="cancel"
            >취소</v-btn
          >
        </v-col>
        <v-col cols="12" md="5"></v-col>
      </v-row>
    </v-container>
  </v-form>
</template>
<script>
import http from "../http-setting";

export default {
  data() {
    return {
      passwordCheck: "",
      idCheck: "",
      data: {
        memberid: "",
        password: "",
        membername: "",
        phone: "",
        email: "",
      },
      rules: {
        email: (v) =>
          !!(v || "").match(/@/) || "이메일형식에 맞게 입력해주세요",
        length: (len) => (v) =>
          (v || "").length >= len || `잘못된 문자 길이, 필수 ${len}`,
        type: (v) =>
          !!(v || "").match(/^(?=.*[a-z])(?=.*\d).+$/) || "영문소문자 + 숫자",
        phone: (v) => !!(v || "").match(/^(?=.*\d).+$/) || "숫자",
      },
    };
  },
  methods: {
    duplicate(id) {
      http
        .post("/signup/idCheck", id)
        .then((res) => {
          if (res.data === true) {
            alert("이미 사용중인 아이디입니다.");
          } else {
            alert("사용가능한 아이디입니다.");
            this.idCheck = res.data;
          }
        })
        .catch((e) => {
          console.log(e);
        });
    },
    resetDuplicate() {
      this.idCheck = true;
    },
    signup() {
      if (
        this.data.memberid === "" ||
        this.data.password === "" ||
        this.passwordCheck === "" ||
        this.data.membername === "" ||
        this.data.phone === "" ||
        this.data.email === ""
      ) {
        alert("공백 없이 작성해주세요");
      } else if (this.idCheck === true || this.idCheck === "") {
        alert("아이디 중복을 확인해주세요.");
      } else if (
        this.data.password != this.passwordCheck ||
        this.data.password === "" ||
        this.data.passwordCheck === ""
      ) {
        alert("비밀번호 중복을 확인해주세요.");
      } else {
        http
          .post("/signup", this.data)
          .then(alert("가입에 성공하였습니다."), this.$router.push("/"))
          .catch((e) => {
            console.log(e);
          });
      }
    },
    cancel() {
      this.$router.push("/");
    },
  },
};
</script>
<style lang=""></style>
