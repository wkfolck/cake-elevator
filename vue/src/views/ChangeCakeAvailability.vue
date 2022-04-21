<template>
<div class = "cake-availability">
  <cake-availability />
</div>
</template>

<script>
import CakeAvailability from '../components/CakeAvailability.vue'
export default {
  components: { CakeAvailability },

}
</script>

<style>

.cake-availability {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

</style>