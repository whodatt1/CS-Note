// 액션
export const increase = (username) => ({
  type: "INCREMENT",
  payload: username,
});
export const decrease = () => ({
  type: "DECREMENT",
});

// 상태
const initstate = {
  username: "",
  number: 1,
};

// 액션의 결과를 걸러내는 역할
const reducer = (state = initstate, action) => {
  switch (action.type) {
    case "INCREMENT":
      return { number: state.number + 1, username: action.payload }; // return 되면 그걸 호출한 쪽에서 받는게 아니라 return 되는 순간 ui 변경이 된다 state가 바뀌어서
    case "DECREMENT":
      return { number: state.number - 1 };
    default:
      return state;
  }
};

export default reducer;
