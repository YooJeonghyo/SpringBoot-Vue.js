import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login.vue";
import Sign from "../views/Sign.vue";
import Info from "../views/Info.vue";
import Members from "../views/Members.vue";
import Correction from "../views/Correction.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Sign",
    component: Sign,
  },
  {
    path: "/info/:id",
    name: "Info",
    component: Info,
    props: true,
  },
  {
    path: "/members",
    name: "Members",
    component: Members,
  },
  {
    path: "/correction/:id",
    name: "Correction",
    component: Correction,
    props: true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
