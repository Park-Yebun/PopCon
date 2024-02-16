<template>
  <div class="wrapper">
    <div class="handle"></div>
  </div>
</template>

<script setup>



// const BottomSheetHeader: FC = () => {
//   return (
//     <Wrapper>
//       <Handle />
//     </Wrapper>
//   );
// };


</script>

<style scoped>
.wrapper {
  height: 48px;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  position: relative;
  padding-top: 16px;
  padding-bottom: 4px;
}

.handle {
  width: 32px;
  height: 4px;
  border-radius: 2px;
  background-color: #d0d0d0;
  margin: auto;
}
</style>