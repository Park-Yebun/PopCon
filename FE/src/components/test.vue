interface BottomSheetMetrics {
  touchStart: {
    sheetY: number; // touchstart에서 BottomSheet의 최상단 모서리의 Y값
    touchY: number; // touchstart에서 터치 포인트의 Y값
  };
  touchMove: {
    prevTouchY?: number; // 다음 touchmove 이벤트 핸들러에서 필요한 터치 포인트 Y값을 저장
    movingDirection: "none" | "down" | "up"; // 유저가 터치를 움직이고 있는 방향 
  };
}

export function useBottomSheet() {
  const sheet = useRef<HTMLDivElement>(null);
  
  const metrics = useRef<BottomSheetMetrics>({
    touchStart: {
      sheetY: 0,
      touchY: 0,
    },
    touchMove: {
      prevTouchY: 0,
      movingDirection: "none",
    },
  });
    
  // Touch Event 핸들러들을 등록한다. 
  useEffect(() => {
    const handleTouchStart = (e: TouchEvent) => {
    
    };
    
    const handleTouchMove = (e: TouchEvent) => {
    
    };
    
    const handleTouchEnd = (e: TouchEvent) => {
    
    };
    
    sheetRef.current.addEventListener('touchstart', handleTouchStart);
    sheetRef.current.addEventListener('touchmove', handleTouchMove);
    sheetRef.current.addEventListener('touchend', handleTouchEnd);
    
    return () => {
      sheetRef.current.removeEventListener('touchstart', handleTouchStart);
      sheetRef.current.removeEventListener('touchmove', handleTouchMove);
      sheetRef.current.removeEventListener('touchend', handleTouchEnd);
    }
  }, [])
    
  return { sheet };
}