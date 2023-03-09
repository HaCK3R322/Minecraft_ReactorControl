import reactorImg from "./images/reactor.png"
import fuelImg from "./images/fuel.png"
import exchangerImg from "./images/componentHeatExchanger.png"
import componentSinkerImg from "./images/componentHeatVent.png"
import overclockedSinkerImg from "./images/overclockedHeatVent.png"
import nuclearImg from "./images/nuclear.png"
import {useEffect, useState} from "react";

function App() {
    const host = "http://193.218.142.195:8080"
    
    const topAddition = 3;
    const leftAddition = 3;

    function calcPosBySlot(slot) {
        let column = Math.floor((slot - 1) % 9);
        let row = Math.ceil(slot / 9) - 1;

        let left = column * (64 + 8) + leftAddition
        let top = row * (64 + 8) + topAddition

        return {
            left: left,
            top: top
        }
    }

    const [fuels, setFuels] = useState([]);
    const [exchangers, setExchangers] = useState([])
    const [sinkers, setSinkers] = useState([])

    useEffect(() => {
        fetch(host + '/getReactorFullState', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            mode: 'cors',
            body: JSON.stringify({ id: 2 })
        })
            .then(data => data.json())
            .then(
            data => {
                setFuels(data.fuels)
                setExchangers(data.exchangers)
                setSinkers(data.sinkers)
            }
        )
    }, []);

    useEffect(() => {
            const intervalId = setInterval(() => {
                fetch(host + '/getReactorFuelState', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    mode: 'cors',
                    body: JSON.stringify({id: 2})
                })
                    .then(response => response.json())
                    .then(data => setFuels(data.fuels))
                    .catch(error => console.log(error));
            }, 5000)
        }, []
    )

    const fuelRodImages = fuels.map((fuel, index) => {
        const slot = fuel.slot;
        console.log(slot)

        let fuelPercentDamage = ((fuel.maxDamage - fuel.damage) / fuel.maxDamage) * 100.0

        let src = null
        if (fuel.name === "ic2:quad_uranium_fuel_rod") {
            src = fuelImg
        } else if(fuel.name === "ic2:nuclear") {
            src = nuclearImg
        }

        return (
            <div style={{
                position: 'absolute',
                left: `${calcPosBySlot(slot).left}px`, // position based on column
                top: `${calcPosBySlot(slot).top}px`, // position based on row
            }}>
                <img
                    key={index}
                    src={src}
                    alt="Fuel Rod"
                    title={`${fuel.maxDamage - fuel.damage} / ${fuel.maxDamage}`}
                />

                <div style={{
                    backgroundColor: "black",
                    position: "absolute",
                    top: "80%",
                    left: "50%",
                    width: `50px`,
                    height: "5px",
                    transform: "translate(-50%, 0)"}}>
                    <div style={{
                        backgroundColor: "green",
                        position: "absolute",
                        top: "0",
                        left: "0",
                        width: `${fuelPercentDamage}%`,
                        height: "5px",
                    }}/>
                </div>
            </div>
        );
    });

    const exchangersImages = exchangers.map((exchanger, index) => {
        const slot = exchanger.slot;
        console.log(slot)

        let src = null
        if (exchanger.name === "ic2:component_heat_exchanger") {
            src = exchangerImg
        }

        return (
            <img
                key={index}
                src={src}
                alt="Exchanger"
                style={{
                    position: 'absolute',
                    left: `${calcPosBySlot(slot).left}px`, // position based on column
                    top: `${calcPosBySlot(slot).top}px`, // position based on row
                }}
            />
        );
    });

    const sinkersImages = sinkers.map((sinker, index) => {
        const slot = sinker.slot;
        console.log(slot)

        let src = null
        if (sinker.name === "ic2:overclocked_heat_vent") {
            src = overclockedSinkerImg
        } else if (sinker.name === "ic2:component_heat_vent") {
            src = componentSinkerImg
        }

        return (
            <img
                key={index}
                src={src}
                alt="Sinker"
                style={{
                    position: 'absolute',
                    left: `${calcPosBySlot(slot).left}px`, // position based on column
                    top: `${calcPosBySlot(slot).top}px`, // position based on row
                }}
            />
        );
    });

    return (
        <div className="App">

            <div id="reactor" style={{
                position: "absolute",
                top: "50%",
                left: "50%",
                transform: "translate(-50%, -50%)"
            }}>
                <img
                    src={reactorImg}
                />
                {fuelRodImages}
                {exchangersImages}
                {sinkersImages}
            </div>

        </div>
    );
}

export default App;
