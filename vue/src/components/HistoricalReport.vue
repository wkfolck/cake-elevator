<template>
  <div>
      <label for="howSearch">How do you want to search?</label>
      <select name="howSearch" v-model="howToSearch" v-on:change = "clearDates()">
          <option value='holidays'>Holidays</option>
          <option value='custom'>Custom Dates</option>
      </select>
      <form v-if="howToSearch==='custom'" v-on:submit.prevent="customDisplayOrders()">
          <label for="startDate">Start Date:</label>
          <input type="date" name="startDate" v-model="startDate">
          <label for="endDate">End Date:</label>
          <input type="date" name="endDate" v-model="endDate">
          <input class= "navButton" type="submit">
      </form>
            <form v-if="howToSearch==='holidays'" v-on:submit.prevent="holidayDisplayOrders()">
                <label for = "holidayName">Holiday: </label>
          <select name="holidayName" v-model="holidayRange">
              <option value = "Christmas">Christmas</option>
              <option value = "Valentines">Valentine's Day</option>
              <option value = "Patricks">St. Patrick's Day</option>
              <option value = "Easter">Easter</option>
              <option value = "Mothers">Mother's Day</option>
              <option value = "Cinco">Cinco De Mayo</option>
              <option value = "Independence">Independence Day</option>
              <option value = "Halloween">Halloween</option>
          </select>
          <input type="number" v-model="holidayYear" min="2000" max="2050">
          <input class= "navButton" type="submit">
      </form>
      <div class = "historicalReport" v-for="order in this.orders" v-bind:key="order.order_id">
          <h2>Order Number: {{order.order_id}}</h2>

            <h2>Customer Details: </h2>

            <h3>Customer Name: {{order.customer_name}}</h3>
            <h3>Customer Address:
                {{order.customer_address}}
                {{order.customer_city}}, {{order.customer_state}} {{order.customer_zip}}</h3>
            <h3>Phone Number: {{order.customer_phone}}</h3>
            <h3>Email: {{order.customer_email}}</h3>
            <h3>Order Date: {{order.order_date}}</h3>
            <h3>Pick-up Date: {{order.pickup_date}}</h3>
            <h3>Completed on: {{order.completed_date}}</h3>

            <h2>Cake Details: </h2>
            <h3>Flavor: {{order.flavor}}</h3>
            <h3>Icing: {{order.icing}}</h3>
            <h3>Size: {{order.size}}</h3>
            <h3>Tiers: {{order.num_of_tiers}}</h3>
            <h3>Shape: {{order.shape}}</h3>
            <h3>Filling: {{order.filling}}</h3>
            <h3>Standard: {{order.is_standard}}</h3>
            <h3>Price: ${{order.price}}</h3>
            <br>
            <br>
      </div>
  </div>
</template>

<script>
import orderService from '../services/OrderService';
export default {
    name: 'historical-report',
    data() {
        return {
            startDate: null,
            endDate: null,
            orders: [],
            howToSearch: null,
            holidayRange: null,
            holidayYear: 2022

        }
    },
    methods: {
        customDisplayOrders() {
            orderService.historicalReport(this.startDate, this.endDate).then(response => {
                this.orders = response.data;
            }).catch (error => {
                console.log(error.data);
            })
        },
        holidayDisplayOrders() {
            if(!this.holidayRange) {
                alert("Please select a holiday range!");
                return;
            }
            let startDate;
            let endDate;
            if(this.holidayRange === "Christmas") {
                startDate = this.holidayYear + '-12-01';
                endDate = this.holidayYear + '-12-25';
            }
            if(this.holidayRange === "Valentines") {
                startDate = this.holidayYear + '-02-01';
                endDate = this.holidayYear + '-02-14';
            }
            if(this.holidayRange === "Patricks") {
                startDate = this.holidayYear + '-03-01';
                endDate = this.holidayYear + '-03-17';
            }
            if(this.holidayRange === "Easter") {
                startDate = this.holidayYear + '-04-01';
                endDate = this.holidayYear + '-04-30';
            }
            if(this.holidayRange === "Mothers") {
                startDate = this.holidayYear + '-05-01';
                endDate = this.holidayYear + '-05-15';
            }
            if(this.holidayRange === "Cinco") {
                startDate = this.holidayYear + '-05-01';
                endDate = this.holidayYear + '-05-05';
            }
            if(this.holidayRange === "Independence") {
                startDate = this.holidayYear + '-07-01';
                endDate = this.holidayYear + '-07-04';
            }
            if(this.holidayRange === "Halloween") {
                startDate = this.holidayYear + '-10-01';
                endDate = this.holidayYear + '-10-31';
            }
            orderService.historicalReport(startDate, endDate).then(response => {
                this.orders = response.data;
            }).catch (error => {
                console.log(error.data);
            })
        },
        clearDates() {
            this.startDate = null;
            this.endDate = null;
        }
    }
}
</script>

<style scoped>
.historicalReport {
    background-color: lightgray;
}
</style>