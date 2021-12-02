<template>
  <div>
    <v-app-bar app color="primary" dark>
      <v-app-bar-nav-icon @click="drawer = !drawer" />

      <v-spacer />

      <div v-show="ok">
        {{ name }}님
        <v-btn depressed @click="logout()" class="ml-5">
          로그아웃
        </v-btn>
      </div>
    </v-app-bar>
    <v-navigation-drawer v-model="drawer" dark app>
      <template #img="props">
        <v-img :gradient="gradient" v-bind="props"> </v-img>
      </template>

      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6">
            SpringBoot & Vue.js
          </v-list-item-title>
          <v-list-item-subtitle>
            회원관리시스템
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider />

      <v-list dense nav>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          :to="item.to"
          active-class="primary"
          class="py-1"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "App",
  mounted() {
    this.isLogin();
  },
  data: () => ({
    ok: "",
    id: sessionStorage.getItem("ID"),
    name: sessionStorage.getItem("name"),
    drawer: false,
    gradient: "rgba(0, 0, 0, .7), rgba(0, 0, 0, .7)",
    items: [],
    right: null,
  }),
  methods: {
    isLogin() {
      let flag = sessionStorage.getItem("ID");
      if (flag === null) {
        this.ok = false;
      } else {
        this.ok = true;
        this.memberLogin();
        if (flag === "20") {
          //임시로 지정한 관리자 계정의 id(PK)가 20인 상황
          this.adminLogin();
        }
      }
    },
    memberLogin() {
      this.items.push(
        { title: "로그인", icon: "mdi-login", to: "/" },
        { title: "회원가입", icon: "mdi-account-plus-outline", to: "/signup" },
        {
          title: "내 정보",
          icon: "mdi-badge-account-outline",
          to: `/info/${this.id}`,
        }
      );
    },
    adminLogin() {
      this.items.push({
        title: "회원관리",
        icon: "mdi-account-group-outline",
        to: "/members",
      });
    },
    logout() {
      sessionStorage.clear();
      this.admin = false;
      this.items = [];
      this.$router.push({
        path: `/`,
      });

      location.reload();
    },
    members() {
      this.$router.push({
        path: "/members",
      });
    },
  },
};
</script>
