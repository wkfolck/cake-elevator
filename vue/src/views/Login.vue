<template>
  <div id="login" class="text-center">

   


    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div id="signIn"
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }" class="shine routing">Need an account?</router-link>
      <button id="submitButton" type="submit" class="navButton">Sign in</button>
    </form>
    <img id="rainbowCake" src= "https://images.unsplash.com/photo-1558301211-0d8c8ddee6ec?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGNha2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"
npm  alt="Picture of Cake">

<div class="container">

	<h1 class="heading" id="cAndSCakesHeading"><strong>Custom Cakes <span>&</span><span> Standard Cakes</span></strong></h1>

	<div class="gallery">
  
		<div class="gallery-item">
			<img class="gallery-image" src="https://t4.ftcdn.net/jpg/02/09/10/27/360_F_209102761_kZuhHqFsVf4mCmPdH4t2udPl1Pj5mWNM.jpg" alt="SLices of Cunstom cakes">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://i.pinimg.com/originals/79/f4/a5/79f4a5903dc60840a974b23d22d24aaf.jpg" alt="4 tier cake standard">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://cdn.shopify.com/s/files/1/0261/6639/4942/products/blushing-birthday-elegant-temptations-bakery-171455.jpg?v=1630114906" alt="Non-traditional cake">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://www.thespruceeats.com/thmb/_ma1KdsyjNJau_pvdhG-O36a3bQ=/2001x2001/smart/filters:no_upscale()/UnicornCupcakesHERO-30b2fd66a8db4a50b90129c402aa25c4.jpg" alt="Cake with Pride">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://blog.wilton.com/wp-content/uploads/2021/01/black-buttercream-500x375.jpg" alt="Say cheese, Cake">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://www.snackncake.com/postpic/2016/11/elegant-baby-shower-sheet-cakes_642536.jpg" alt="CupCakes">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://www.brides.com/thmb/KDj0gkEd8zgENlAhxv-KYdVB9fU=/932x1382/filters:no_upscale():max_bytes(150000):strip_icc()/fdas-2fcbf1d2e8d84b5db1fcc0cd486880a5.png">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="http://coppycakes.com/wp-content/uploads/2020/09/23844925_2068318193389724_3897331173025094952_n.jpg">
		</div>

		<div class="gallery-item">
			<img class="gallery-image" src="https://i0.wp.com/artandhome.net/wp-content/uploads/2020/04/Blue-Birthday-Cakes-Blue-Butterflies-683x1024.jpg?ssl=1">
		</div>

	</div>

</div>

  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
