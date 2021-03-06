import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import OrderStandardCake from '../views/OrderStandardCake.vue'
import OrderCustomCake from '../views/OrderCustomCake.vue'
import OrderConfirmed from '../views/OrderConfirmed.vue'
import PendingOrders from '../views/ViewPendingOrders.vue'
import DefineCake from '../views/DefineCake.vue'
import CakeAvailability from '../views/ChangeCakeAvailability.vue'
import EditCakeOptions from '../views/EditCakeOptions.vue'
import History from '../views/History.vue'
import Calendar from '../views/Calendar.vue'
import Search from '../views/Search.vue'
import store from '../store/index'
import CustomerHome from '../views/CustomerHome.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/orderstandardcake",
      name: "orderstandardcake",
      component: OrderStandardCake,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/ordercustomcake",
      name: "ordercustomcake",
      component: OrderCustomCake,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/orderconfirmed",
      name: "orderconfirmed",
      component: OrderConfirmed,
      props: true,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/pendingorders",
      name: "pendingorders",
      component: PendingOrders,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/definecake",
      name: "definecake",
      component: DefineCake,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/cakeavailability",
      name: "cakeavailability",
      component: CakeAvailability,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/editcakeoptions",
      name: "editcakeoptions",
      component: EditCakeOptions,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/historicalreport",
      name: "historicalreport",
      component: History,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/calendar",
      name: "calendar",
      component: Calendar,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/search",
      name: "search",
      component: Search,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/customerhome",
      name: "customer-home",
      component: CustomerHome,
      meta: {
        requiresAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
