import { createWebHistory, createRouter } from "vue-router";

const routes =  [
  {
    path: "/",
    alias: "/clients",
    name: "clients",
    component: () => import("./components/clientsList")
  },
  {
    path: "/clients/:id",
    name: "client-details",
    component: () => import("./components/client")
  },
  {
    path: "/add",
    name: "add",
    component: () => import("./components/Addclient")
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;