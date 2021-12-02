<template>
  <v-container>
    <v-row>
      <v-col>
        <v-data-table
          :headers="headers"
          :items="form"
          :items-per-page="10"
          class="elevation-1"
        >
          <template v-slot:[`item`]="{ item }">
            <tr @click="clicktest(item)">
              <td>{{ item.memberid }}</td>
              <td>{{ item.membername }}</td>
              <td>{{ new Date(item.createdDate).toLocaleString() }}</td>
              <td>{{ item.phone }}</td>
              <td>{{ item.email }}</td>
            </tr>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import http from "../http-setting";
export default {
  data() {
    return {
      form: [],
      test: "test",
      headers: [
        {
          text: "아이디",
          align: "start",
          sortable: false,
          value: "memberid",
        },

        { text: "이름", value: "membername" },
        { text: "등록일자", value: "createdDate" },
        { text: "전화번호", value: "phone" },
        { text: "메일주소", value: "email" },
      ],
    };
  },
  methods: {
    getMembers() {
      http
        .get("/members")
        .then((res) => {
          this.form = res.data;
        })
        .catch((e) => {
          console.log(e);
        });
    },
    clicktest(item) {
      this.$router.push({
        path: `/info/${item.id}`,
      });
    },
  },
  mounted() {
    this.getMembers();
  },
};
</script>
<style lang=""></style>
