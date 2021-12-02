<template lang="">
  <v-form>
    <v-container fluid grid-list-sm pa-0>
      <v-row>
        <v-col cols="12" md="3"></v-col>
        <v-col cols="12" md="6">
          <h2 class="text-center mt-5 mb-5">{{ title }}</h2>

          <v-text-field
            v-model="form.memberid"
            label="아이디"
            outlined
            readonly
          ></v-text-field>
          <v-text-field
            v-model="form.membername"
            label="사용자명"
            outlined
            readonly
          ></v-text-field>
          <v-text-field
            v-model="form.email"
            label="이메일"
            outlined
            readonly
          ></v-text-field>
          <v-text-field
            v-model="form.phone"
            label="전화번호"
            outlined
            readonly
          ></v-text-field>
          <v-text-field
            v-model="form.createdDate"
            label="가입일"
            outlined
            readonly
          ></v-text-field>
        </v-col>
        <v-col cols="12" md="3"></v-col>
      </v-row>

      <v-row>
        <v-col cols="12" md="5"></v-col>
        <v-col cols="12" md="2">
          <v-btn block color="primary" dark @click="toCorrection()">수정</v-btn>
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
    const paramId = this.$route.params.id;
    return {
      paramId: paramId,
      id: sessionStorage.getItem("ID"),
      title: "내 정보",
      form: {},
    };
  },
  created() {
    this.getInfo();
  },
  watch: {
    $route() {
      location.reload();
    },
  },
  methods: {
    getInfo() {
      if (this.paramId !== this.id.toString()) {
        this.id = this.paramId;
        this.title = "회원 관리 정보";
      }
      http
        .get(`/info/${this.id}`)
        .then((res) => {
          this.form = res.data;
          this.form.createdDate = new Date(
            this.form.createdDate
          ).toLocaleString();
        })
        .catch((e) => {
          console.log("에러 : " + e);
        });
    },
    toCorrection() {
      this.$router.push({
        path: `/correction/${this.form.id}`,
      });
    },
  },
};
</script>
<style lang=""></style>
