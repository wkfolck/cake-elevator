<template>
  <div>
    <form class="customCakeOrder" v-on:submit.prevent="submitCake()">
         <div>
          <label for="flavor">Flavor:</label>
          <select  v-model="newCake.flavor" name="flavor">
              <option v-for="flavor in cakeFlavors" v-bind:key="flavor.optionId" :value="flavor.optionName">{{flavor.optionName}} (+${{flavor.additionalPrice}})</option>
          </select>
          </div>
          <div>
          <label for="icing">Icing:</label>
          <select v-model="newCake.icing" name="icing">
              <option v-for="icing in cakeIcings" v-bind:key="icing.optionId" :value="icing.optionName">{{icing.optionName}} (+${{icing.additionalPrice}})</option>
          </select>
          </div>
         <div>
          <label for="filling">Filling:</label>
          <select v-model="newCake.filling" name="filling">
              <option v-for="filling in cakeFillings" v-bind:key="filling.optionId" :value="filling.optionName">{{filling.optionName}} (+${{filling.additionalPrice}})</option>
              <option value="None">None</option>
          </select>
          </div>
          <div>
          <label for="size">Size:</label>
          <select v-model="newCake.size" name="size">
              <option v-for="size in cakeSizes" v-bind:key="size.optionId" :value="size.optionName">{{size.optionName}} (+${{size.additionalPrice}})</option>
          </select>
          </div>
          <div>
          <label for="tiers">Tiers:</label>
          <select v-model="newCake.numOfTiers" name="tiers">
              <option value=1>1 tier</option>
              <option value=2>2 tiers (+ $5.00)</option>
              <option value=3>3 tiers (+ $10.00)</option>
          </select>
          </div>
          <div>
          <label for="shape">Shape:</label>
          <select v-model="newCake.shape" name="tiers">
              <option v-for="shape in cakeShapes" v-bind:key="shape.optionId" :value="shape.optionName">{{shape.optionName}} (+${{shape.additionalPrice}})</option>
          </select>
          <div>
            <label for="url">Enter image URL (optional)</label>
            <input type="text" name="url" v-model="newCake.imageURL">
          </div>
          <h2>Base Price: ${{getPrice()}}</h2>
          <input type="submit" class= "navButton">
          </div>
      </form>
  </div>
</template>

<script>
import orderService from '../services/OrderService'
export default {
    name: 'define-cake-form',
    data() {
      return {
        newCake: {filling: 'None', numOfTiers: 1, standard: true, available: true, price: 20.00},
            cakeFlavors: [],
            cakeIcings: [],
            cakeFillings: [],
            cakeSizes: [],
            cakeShapes: [],
      }
    },
    created() {
            orderService.getCakeFlavors().then(
                response => {
                    console.log(response.data);
                    this.cakeFlavors = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeIcings().then(
                response => {
                    console.log(response.data);
                    this.cakeIcings = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeFillings().then(
                response => {
                    console.log(response.data);
                    this.cakeFillings = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeSizes().then(
                response => {
                    console.log(response.data);
                    this.cakeSizes = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
            orderService.getCakeShapes().then(
                response => {
                    console.log(response.data);
                    this.cakeShapes = response.data;
                }
            )
            .then(error => {
                console.log(error.data);
            });
        },
    methods: {
        getPrice() {
            let basePrice = 20.00 + this.getFlavorPrice(this.newCake.flavor) + this.getIcingPrice(this.newCake.icing) +
            this.getFillingPrice(this.newCake.filling) + this.getSizePrice(this.newCake.size) + this.getShapePrice(this.newCake.shape);
            if(this.newCake.numOfTiers == 2) {
                basePrice += 5.00;
            }
            if(this.newCake.numOfTiers == 3) {
                basePrice += 10.00;
            }
            if(this.addMessage) {
                basePrice += 5.00;
            }
            return basePrice;
        },
        getFlavorPrice(flavor) {
           const flavorArray = this.cakeFlavors;
           let results = 0;
           for(let i = 0; i < flavorArray.length; i++) {
               if(flavorArray[i].optionName == flavor) {
                   results = flavorArray[i].additionalPrice;
               }
           }
           return results;
        },
        getIcingPrice(icing) {
           const icingArray = this.cakeIcings;
           let results = 0;
           for(let i = 0; i < icingArray.length; i++) {
               if(icingArray[i].optionName == icing) {
                   results = icingArray[i].additionalPrice;
               }
           }
           return results;
        },
        getFillingPrice(filling) {
           const fillingArray = this.cakeFillings;
           let results = 0;
           for(let i = 0; i < fillingArray.length; i++) {
               if(fillingArray[i].optionName == filling) {
                   results = fillingArray[i].additionalPrice;
               }
           }
           return results;
        },
        getSizePrice(size) {
           const sizeArray = this.cakeSizes;
           let results = 0;
           for(let i = 0; i < sizeArray.length; i++) {
               if(sizeArray[i].optionName == size) {
                   results = sizeArray[i].additionalPrice;
               }
           }
           return results;
        },
        getShapePrice(shape) {
           const shapeArray = this.cakeShapes;
           let results = 0;
           for(let i = 0; i < shapeArray.length; i++) {
               if(shapeArray[i].optionName == shape) {
                   results = shapeArray[i].additionalPrice;
               }
           }
           return results;
        },
        submitCake() {
          this.newCake.price = this.getPrice();
          orderService.orderCustomCake(this.newCake).then((response) => {
            console.log(response);
            this.$router.push({name: 'home'});
        })
        .catch((error) => {
           console.log(error.response.data);
           });
        }
    }
}
</script>

<style scoped>

</style>