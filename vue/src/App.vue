<template>
<div>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'customer-home' }" tag="button" class="navButton" >Home</router-link>
      <router-link v-bind:to="{ name: 'orderstandardcake' }"  tag="button" class="navButton">Order An In-Stock Cake</router-link>
      <router-link v-bind:to="{ name: 'ordercustomcake' }"  tag="button" class="navButton"> Order a Custom Cake</router-link>
      <router-link v-bind:to="{ name: 'login' }"  tag="button" class="navButton" v-if="$store.state.token == ''">Employee Login</router-link>
    </div>

  <div id="nav" v-if="$store.state.token != ''">
     <router-link v-bind:to="{ name: 'definecake' }" v-if="$store.state.token != ''" tag="button" class="navButton"> Add a Cake to Inventory</router-link>
      <router-link v-bind:to="{ name: 'cakeavailability' }" v-if="$store.state.token != ''" tag="button" class="navButton"> Change Cake Availability</router-link>
      <router-link v-bind:to="{ name: 'editcakeoptions' }" v-if="$store.state.token != ''" tag="button" class="navButton"> Edit Cake Options</router-link>
      <router-link v-bind:to="{ name: 'pendingorders' }" v-if="$store.state.token != ''" tag="button" class="navButton">View Pending Orders</router-link>
      <router-link v-bind:to="{ name: 'historicalreport' }" v-if="$store.state.token != ''" tag="button" class="navButton">View Historical Report</router-link>
      <router-link v-bind:to="{ name: 'calendar' }" v-if="$store.state.token != ''" tag="button" class="navButton">Calendar of Upcoming Orders</router-link>
      <router-link v-bind:to="{ name: 'search' }" v-if="$store.state.token != ''" tag="button" class="navButton">Search for Orders</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="button" class="navButton"> Logout</router-link>
  </div>
    <router-view />

    
  
          
   </div>
   <footer>
     <div class="site-footer">
      <div class="container">
        <div class="row">
          <div class="column" id="aboutus" >
            <h6>About Us</h6>
            <p class="text-justify">Passionate bakers Nia-Marii Bratton, Bill Folck, Vanessa Garrett, and Grace Lefever come together with the help of investors Patrick Devanney and Christopher Guarnera to bring to you Cake Elevator. Choose from one of our delicious standard cakes or customize your own! Here at Cake Elevator, we seek to "elevate" your taste buds.</p>
          </div>

          <div class="column" id="hours">
            <h6>Hours</h6>
            <ul class="footer-links">
              <li>Monday: 9am - 6pm</li>
              <li>Tuesday: 9am - 6pm</li>
              <li>Wednesday: 9am - 6pm</li>
              <li>Thursday: 9am - 6pm</li>
              <li>Friday: 9am - 10pm</li>
              <li>Saturday: 9am - 10pm</li>
              <li>Sunday: Closed</li>

            </ul>
          </div>

          <div class="column" id="locations">
            <h6>Locations</h6>
            <ul class="footer-links">
              <li>123 Main St</li>
              <li>Cleveland, NY 44201</li>
              <li>cakeelevator@gmail.com</li>
              <li>------</li>
              <li>8900 Broadway</li>
              <li>Cleveland, NY 44225</li>
              <li>cakeelevator2@gmail.com</li>
            
            </ul>
          </div>
        </div>
        <hr>
      </div>
      <div class="container">
        <div class="row">
          <div class="column">
            <p class="copyright-text">Copyright &copy; 2022 All Rights Reserved by Cake Elevator LLC.
         
            </p>
          </div>

          <div class="column">
            <ul class="social-icons">
              <li><a class="facebook" href="http://facebook.com" target="popup"><i class="fa-brands fa-facebook"></i></a></li>
              <li><a class="instagram" href="https://instagram.com" target="popup"><i class="fa-brands fa-instagram"></i></a></li> 
              <li><a class="twitter" href="https://twitter.com" target="popup"><i class="fa-brands fa-twitter"></i></a></li>
              <li><a class="linkedin" href="https://linkedin.com" target="popup"><i class="fa-brands fa-linkedin"></i></a></li>   
            </ul>
          </div>
        </div>
      </div>
   </div>
</footer>
</div>
</template>

