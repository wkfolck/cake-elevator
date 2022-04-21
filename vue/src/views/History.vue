<template>
<div class="historical-report">
  <historical-report />
</div>
</template>

<script>
import HistoricalReport from '../components/HistoricalReport.vue'
export default {
    components: {HistoricalReport}
}
</script>

<style>

.historical-report {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

</style>
HistoricalReport