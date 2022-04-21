<template>
<div>
  <div class="cakeAvailability" v-for="cake in standardCakes" v-bind:key="cake.cakeId">
          <label for="premade">
              <div>
                  <h2 v-if="cake.available">Currently Available</h2>
                  <h2 v-if="!cake.available">Currently Unavailable</h2>
          <img v-if="cake.imageURL" v-bind:src="cake.imageURL">
              </div>
          {{cake.size}} {{cake.shape}} {{cake.numOfTiers}}-tiered {{cake.flavor}} Cake With {{cake.icing}} Icing, {{cake.filling}} Filling: ${{cake.price}}
          <br>
          <br>
          <br>
          <button class="navButton" v-if="!cake.available" v-on:click="makeAvailable(cake.cakeId)" >Make Available</button>
          <button class="navButton" v-if="cake.available" v-on:click="makeUnavailable(cake.cakeId)" >Make Unavailable</button>
          <br>
          <br>
          <br>
          </label>
      </div>
</div>
</template>

<script>
import orderService from '../services/OrderService'
export default {
name: "cake-availability",
    data() {
        return {
            standardCakes: [],
        }
    },
    created() {
            orderService.getStandardCakes().then(
                response => {
                    console.log(response.data);
                    this.standardCakes = response.data;
                }
            );
        },
        methods: {
            makeAvailable(id) {
                orderService.makeCakeAvailable(id).then(response => {
                    console.log(response.data);
                    this.$router.go();
                })
            },
            makeUnavailable(id) {
                orderService.makeCakeUnavailable(id).then(response => {
                    console.log(response.data);
                    this.$router.go();
            })
        }
        }
}
</script>

<style>
img {
    width: 200px;
    height: 100%;
}
.availabilityButton {
    color: black;
    width: 200px;
    height: 100%;
}

</style>