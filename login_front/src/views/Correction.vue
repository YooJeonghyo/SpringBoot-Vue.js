<template lang="">
  <div>
    <v-form>
      <v-container fluid grid-list-sm pa-0>
        <v-row>
          <v-col cols="12" md="3"></v-col>
          <v-col cols="12" md="6">
            <h2 class="text-center mt-5 mb-5">정보 수정</h2>

            <v-text-field
              v-model="form.memberid"
              label="아이디"
              outlined
              readonly
              filled
            ></v-text-field>
            <v-text-field
              v-model="form.membername"
              label="사용자명"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="form.email"
              label="이메일"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="form.phone"
              label="전화번호"
              outlined
            ></v-text-field>
            <v-text-field
              v-model="createdDate"
              label="가입일"
              outlined
              filled
              readonly
            ></v-text-field>
            <v-text-field
              v-model="modifiedDate"
              label="수정일"
              outlined
              filled
              readonly
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="3"></v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="5"></v-col>
          <v-col cols="12" md="1">
            <v-btn block color="primary" dark @click="correction()">수정</v-btn>
          </v-col>
          <v-col cols="12" md="1">
            <v-btn block color="blue-grey darken-1" dark @click="deleteMember()"
              >삭제</v-btn
            >
          </v-col>
          <v-col cols="12" md="5"></v-col>
        </v-row>
      </v-container>
    </v-form>

    <v-dialog v-model="dialog" max-width="400">
      <v-card>
        <v-card-title class="headline grey lighten-1" dark>
          회원정보 삭제
        </v-card-title>
        <v-card-text>
          <div class="text-h6 pt-5">
            정말 회원정보를 삭제하시겠습니까?
          </div>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red accent-4" text @click="deleteOk()">
            삭제
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
  data() {
    const id = this.$route.params.id;
    return {
      id: id,
      form: {},
      createdDate: "",
      modifiedDate: "",
      dialog: false,
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    getInfo() {
      http
        .get(`/info/${this.id}`)
        .then((res) => {
          this.form = res.data;
          this.createdDate = new Date(this.form.createdDate).toLocaleString();
          this.modifiedDate = new Date(this.form.modifiedDate).toLocaleString();
        })
        .catch((e) => {
          console.log("에러 : " + e);
        });
    },
    correction() {
      http
        .put("/correction", this.form)
        .then((res) => {
          alert("수정되었습니다.");
          if (sessionStorage.getItem("ID") === res.data.toString()) {
            sessionStorage.setItem("name", this.form.membername);
          }
          this.$router.push({
            path: `/info/${res.data}`,
          });
          location.reload();
        })
        .catch((e) => {
          console.log("에러 : " + e);
        });
    },
    deleteOk() {
      this.dialog = false;
      if (this.form.id === 20) {
        alert("관리자 계정은 삭제할 수 없습니다.");
      } else {
        alert("삭제합니다");
        http
          .delete(`/correction/${this.form.id}`)
          .then(
            () => alert("삭제되었습니다"),
            this.$router.push({
              path: `/members`,
            }),
            location.reload()
          )
          .catch((e) => {
            console.log("에러 : " + e);
          });
      }
    },
    deleteMember() {
      if (sessionStorage.getItem("ID") !== "20") {
        alert("관리자에게 문의하세요");
      } else {
        this.dialog = true;
      }
    },
  },
};
</script>
<style lang=""></style>
