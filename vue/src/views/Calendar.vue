<template>
<div class="upcoming-orders">
  <upcoming-calendar />
</div>
</template>

<script>
import UpcomingCalendar from '../components/UpcomingCalendar.vue'
export default {
  components: { UpcomingCalendar },

}
</script>

<style>

.upcoming-orders {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

</style>