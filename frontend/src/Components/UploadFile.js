import React from "react";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";
import Link from "@mui/material/Link";
import FileUploadOutlinedIcon from "@mui/icons-material/FileUploadOutlined";
import * as XLSX from "xlsx";

const UploadFile = () => {
  const input = React.useRef(null);
  const [placeholder, setPlaceholder] = React.useState("");
  const [UserDatas, setUserDatas] = React.useState([]);

  const readExcel = async (file) => {
    const fileReader = await new FileReader();
    fileReader.readAsArrayBuffer(file);

    fileReader.onload = (e) => {
      const bufferArray = e?.target.result;
      const wb = XLSX.read(bufferArray, { type: "buffer" });
      const wsname = wb.SheetNames[0];
      const ws = wb.Sheets[wsname];

      const data = XLSX.utils.sheet_to_json(ws);
      console.log(data)
       
      const userDatas = [ sessionStorage.getItem("userId") ];
      try {
        for (var i = 0; i < data.length; i++) {
          const userData = {};
          userData["CNumber"] = data[i]["학수강좌번호"] + "-" + data[i][''];
          userData["ClassScore"] = data[i]["등급"];
          if (data[i]["학기"] === "여름학기") {
            userData["TNumber"] = data[i]["년도"] + "_ss";
          } else if (data[i]["학기"] === "겨울학기") {
            userData["TNumber"] = data[i]["년도"] + "_ws";
          } else {
            userData["TNumber"] = data[i]["년도"] + "_" + data[i]["학기"][0];
          }
          userDatas.push(userData);
        }
      } catch(error) {
        alert("올바른 형식의 엑셀 파일을 업로드해주세요.");
        setUserDatas([]);
        setPlaceholder("");
        input.current.value = null;
      }
      setUserDatas(userDatas);
    };
  };

  const jsonResult = {
    email: UserDatas[0],
    userDataList: UserDatas.slice(1),
  };

  const onClickInput = async (e) => {
    e.preventDefault();
    if (!placeholder) {
      console.log("입력 파일이 없습니다.");
      alert("입력 파일이 없습니다.");
    } else {
      console.log(JSON.stringify(jsonResult));
      try {
        const response = await fetch("/input/gradeFile", {
          method: "post",
          headers: {
            "content-type": "application/json",
          },
          body: JSON.stringify(jsonResult),
        });
        if(response.ok) {
          alert("성적이 입력되었습니다!");
          window.location.replace("/result");
        } else if (response.status === 400) {
          alert("회원 정보와 입력 파일의 이수학기 수가 일치하지 않습니다.");
          window.location.href = "/input";
        } else {
          console.error("성적 입력 실패 : ", response.body);
        }
      } catch (error) {
        console.error("성적 입력 실패 : ", error);
      }

    }
  };

  return (
    <div>
      <Stack className="input_area" flexDirection={"row"}>
        <Stack
          className="upload_btn"
          flexDirection={"row"}
          onClick={() => {
            input.current?.click();
          }}
        >
          <span className="upload_icon">
            <FileUploadOutlinedIcon />
          </span>
          <span className="upload">파일 업로드</span>
        </Stack>
        <Box className="file_name" sx={{ overflow: "hidden" }}>
          {placeholder}
        </Box>
        <input
          type={"file"}
          accept={".xlsx,.xls"}
          ref={input}
          style={{ display: "none" }}
          onChange={(e) => {
            const file = e.target.files[0];
            readExcel(file);
            setPlaceholder(file.name);
          }}
        />
      </Stack>
      <Box className="btn_area" style={{ marginBottom: 50 }}>
        <Link to="/result">
          <button className="btn" onClick={onClickInput}>
            계산
          </button>
        </Link>
      </Box>
    </div>
  );
};

export default UploadFile;
